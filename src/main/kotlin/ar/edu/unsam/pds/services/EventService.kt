package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.EventRequestDto
import ar.edu.unsam.pds.dto.response.EventDetailResponseDto
import ar.edu.unsam.pds.dto.response.EventResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.mappers.EventMapper
import ar.edu.unsam.pds.mappers.ScheduleMapper
import ar.edu.unsam.pds.models.Event
import ar.edu.unsam.pds.models.Schedule
import ar.edu.unsam.pds.models.enums.EventType
import ar.edu.unsam.pds.repository.ClassroomRepository
import ar.edu.unsam.pds.repository.EventRepository
import ar.edu.unsam.pds.repository.ScheduleRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val courseService: CourseService,
    private val periodService: PeriodService,
    private val classroomRepository: ClassroomRepository,
    private val scheduleRepository: ScheduleRepository
) {

    companion object {
        val INSTITUTIONAL_TYPES = listOf(EventType.CHARLA, EventType.SEMINARIO, EventType.CONFERENCIA)
        val ACADEMIC_TYPES      = listOf(EventType.CURSADA, EventType.PARCIAL, EventType.FINAL)
    }

    fun getAll(): List<Event> {
        return eventRepository.findAll()
    }

    // ──────────────────────────────────────────────
    // NUEVO: eventos institucionales (sin curso)
    // ──────────────────────────────────────────────
    fun getStandaloneEvents(): List<Event> {
        return eventRepository.findStandaloneByTypes(INSTITUTIONAL_TYPES)
    }

    fun searchStandaloneEvents(query: String): List<Event> {
        return eventRepository.searchStandaloneByName(INSTITUTIONAL_TYPES, query)
    }

    // ──────────────────────────────────────────────
    // NUEVO: validación de coherencia tipo ↔ curso
    // ──────────────────────────────────────────────
    fun validateEventTypeCourseCoherence(type: EventType, courseID: String?) {
        if (type in ACADEMIC_TYPES && courseID.isNullOrBlank()) {
            throw ValidationException("Los eventos de tipo ${type.name} requieren un curso asociado")
        }
        if (type in INSTITUTIONAL_TYPES && !courseID.isNullOrBlank()) {
            throw ValidationException("Los eventos de tipo ${type.name} no deben tener un curso asociado")
        }
    }

    @Transactional(readOnly = true)
    fun searchBy(classroomID: String, date: LocalDate): List<Event> =
        eventRepository.findEventsByClassroomAndDate(classroomID, date, date.dayOfWeek)

    fun findByID(id: String?): Event {
        val eventID = UUID.fromString(id)
        return eventRepository.findById(eventID).orElseThrow {
            NotFoundException("Evento no encontrado para el uuid suministrado")
        }
    }

    fun getEvent(eventId: String): EventResponseDto? {
        val eventUUID = UUID.fromString(eventId)
        val matchingEvent = eventRepository.findById(eventUUID)
            .orElseThrow { NotFoundException("Evento no encontrado para el uuid suministrado") }

        return EventMapper.buildEventDto(matchingEvent)
    }

    fun getEventDetail(eventId: String): EventDetailResponseDto? {
        val eventUUID = UUID.fromString(eventId)
        val matchingEvent = eventRepository.findById(eventUUID)
            .orElseThrow { NotFoundException("Evento no encontrado para el uuid suministrado") }

        return EventMapper.buildEventDetailDto(matchingEvent)
    }

    fun addSchedules(event: Event, schedules: List<Schedule>) {
        event.addSchedules(schedules)
    }

    fun addPeriod(event: Event, periodID: String?) {
        val period = periodService.getById(periodID)
        event.addPeriod(period)
    }

    @Transactional
    fun update(eventDto: EventRequestDto): Event {

        val eventType = EventType.valueOf(eventDto.type)
        validateEventTypeCourseCoherence(eventType, eventDto.courseID)

        val existingEvent = findByID(eventDto.id)

        existingEvent.apply {
            name              = eventDto.name
            isApproved        = eventDto.isApproved
            isCancelled       = eventDto.isCancelled
            course            = if (!eventDto.courseID.isNullOrBlank()) courseService.findByID(eventDto.courseID) else null
            period            = if (!eventDto.periodID.isNullOrBlank()) periodService.getById(eventDto.periodID) else null
            type              = eventType
            details           = eventDto.details ?: ""
            customPeriodStart = eventDto.customPeriodStart
            customPeriodEnd   = eventDto.customPeriodEnd
        }

        val currentById = existingEvent.schedules.associateBy { it.id }.toMutableMap()

        eventDto.schedules.forEach { schDto ->
            val schId = schDto.id?.let(UUID::fromString)

            if (schId != null && currentById.containsKey(schId)) {
                val cur = currentById.remove(schId)!!
                cur.startTime = schDto.startTime
                cur.endTime   = schDto.endTime
                cur.weekDay   = ScheduleMapper.toDayOfWeek(schDto.weekDay)
                cur.date      = schDto.date
                cur.isVirtual = schDto.isVirtual
                cur.classroom = if (cur.isVirtual) null
                else classroomRepository.findById(UUID.fromString(schDto.classroomId)).orElseThrow()
            } else {
                val newSch = ScheduleMapper.buildSchedule(schDto).apply {
                    event = existingEvent
                    if (!isVirtual) {
                        classroom = classroomRepository.findById(
                            UUID.fromString(schDto.classroomId)
                        ).orElseThrow()
                    }
                }
                existingEvent.schedules.add(newSch)
            }
        }

        currentById.values.forEach { orphan ->
            orphan.assignedUsers.forEach { user -> user.scheduleList.remove(orphan) }
            orphan.assignedUsers.clear()
            existingEvent.schedules.remove(orphan)
            scheduleRepository.delete(orphan)
        }

        return existingEvent
    }

    @Transactional
    fun create(newEvent: Event): Event {
        eventRepository.save(newEvent)
        return newEvent
    }

    @Transactional
    fun delete(id: String) {
        val eventToDelete = findByID(id)

        eventToDelete.schedules.forEach { schedule ->
            schedule.assignedUsers.forEach { user ->
                user.scheduleList.remove(schedule)
            }
            schedule.assignedUsers.clear()

            eventRepository.delete(eventToDelete)
        }
    }

    fun getPendingRequests(): List<Event> {
        return eventRepository.findByIsApprovedIsNull()
    }

    @Transactional
    fun approve(id: String): Event {
        val event = findByID(id)
        event.isApproved = true
        return eventRepository.save(event)
    }

    @Transactional
    fun reject(id: String): Event {
        val event = findByID(id)
        event.isApproved = false
        return eventRepository.save(event)
    }
}