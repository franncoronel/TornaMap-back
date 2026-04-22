package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.EventRequestDto
import ar.edu.unsam.pds.dto.response.CustomResponse
import ar.edu.unsam.pds.mappers.EventMapper
import ar.edu.unsam.pds.mappers.ScheduleMapper
import ar.edu.unsam.pds.repository.ClassroomRepository
import ar.edu.unsam.pds.services.CourseService
import ar.edu.unsam.pds.services.EventService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("api/events")
@CrossOrigin("*")
class EventController : UUIDValid() {
    @Autowired
    private lateinit var classroomRepository: ClassroomRepository
    @Autowired
    lateinit var eventService: EventService
    @Autowired
    lateinit var courseService: CourseService

    @GetMapping("/{classroomID}/{date}")
    @Operation(summary = "Get all events in a given classroom")
    fun getByClassroom(
        @PathVariable classroomID: String,
        @PathVariable date: String
    ): ResponseEntity<CustomResponse> {
        val parsedDate = LocalDate.parse(date)
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Eventos obtenidos con exito",
                data = eventService.searchBy(classroomID, parsedDate).flatMap { event ->
                    event.schedules.map { schedule -> EventMapper.buildEventCardDto(event, schedule) }
                }.sortedBy { event -> event.schedules[0].startTime }
            )
        )
    }

    @GetMapping("{eventID}")
    @Operation(summary = "Get an event by ID")
    fun getEvent(@PathVariable(required = true) eventID: String): ResponseEntity<CustomResponse> {
        validatedUUID(eventID)
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Evento obtenido con exito",
                data = eventService.getEvent(eventID)
            )
        )
    }

    @GetMapping("detail/{eventID}")
    @Operation(summary = "Get an event detail by ID")
    fun getEventDetail(
        @PathVariable(required = true) eventID: String
    ): ResponseEntity<CustomResponse> {
        validatedUUID(eventID)
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Evento obtenido con exito",
                data = eventService.getEventDetail(eventID)
            )
        )
    }

    @GetMapping
    @Operation(summary = "Get all events")
    fun getAllEvents(
    ): ResponseEntity<CustomResponse> {
        val events = eventService.getAll()
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Events encontrados",
                data = events.map { EventMapper.buildEventDto(it) }
            )
        )
    }


    @PostMapping
    @Operation(summary = "Create an event")
    fun createEvent(
        @RequestBody @Valid eventDTO: EventRequestDto
    ): ResponseEntity<CustomResponse> {

        val course = if (!eventDTO.courseID.isNullOrBlank()) courseService.findByID(eventDTO.courseID) else null
        val event = EventMapper.buildEvent(eventDTO, course)

        val builtSchedules = eventDTO.schedules.map { scheduleDto ->
            val schedule = ScheduleMapper.buildSchedule(scheduleDto)
            if (!schedule.isVirtual) {
                val classroom = classroomRepository.findById(UUID.fromString(scheduleDto.classroomId)).orElseThrow()
                schedule.classroom = classroom
            }
            schedule
        }

        eventService.addSchedules(event, builtSchedules)

        if (!eventDTO.periodID.isNullOrBlank()) {
            eventService.addPeriod(event, eventDTO.periodID)
        }

        val newEvent = eventService.create(event)

        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Event creado con éxito",
                data = EventMapper.buildEventDto(newEvent)
            )
        )
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete an event by ID")
    fun deleteEvent(
        @PathVariable id: String
    ): ResponseEntity<CustomResponse> {
        validatedUUID(id)
        eventService.delete(id)
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Event eliminado con exito",
                data = null
            )
        )
    }


    @PutMapping
    @Operation(summary = "Edit an event by ID")
    fun editEvent(
        @RequestBody @Valid eventDTO: EventRequestDto
    ): ResponseEntity<CustomResponse> {
        requireNotNull(eventDTO.id) { "El ID del evento no debe ser nulo" }

        val updatedEvent = eventService.update(eventDTO)       // ← pasamos sólo el DTO

        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Event editado con éxito",
                data = EventMapper.buildEventDto(updatedEvent)
            )
        )
    }
}