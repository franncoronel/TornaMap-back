package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.EventRequestDto
import ar.edu.unsam.pds.dto.request.ScheduleRequestDto
import ar.edu.unsam.pds.dto.response.EventResponseDto
import ar.edu.unsam.pds.dto.response.ScheduleResponseDto
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.EventService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class EventControllerTest {
    @Mock
    private lateinit var eventService: EventService
//    private lateinit var eventController: EventController

    private lateinit var eventReq: EventRequestDto
    private lateinit var eventRes: EventResponseDto
    private lateinit var scheduleReq: ScheduleRequestDto
    private lateinit var scheduleRes: ScheduleResponseDto
    private lateinit var principal: Principal
    private lateinit var user: User

    @BeforeEach
    fun setUp(eventController : EventController) {
        eventController.eventService = eventService

        scheduleReq = ScheduleRequestDto(
            id = UUID.randomUUID().toString(),
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            weekDay = DayOfWeek.FRIDAY,
            date = LocalDate.now(),
            isVirtual = true,
            classroomId = null,
        )

        scheduleRes = ScheduleResponseDto(
            id = UUID.randomUUID().toString(),
            startTime = LocalTime.now(),
            endTime = LocalTime.now(),
            weekDay = DayOfWeek.FRIDAY,
            date = LocalDate.now(),
            isVirtual = true,
            professors = listOf("Prof 1", "Prof 2"),
            classroom = null
        )

        eventReq = EventRequestDto(
            courseID = UUID.randomUUID().toString(),
            name = "Event Name",
            schedules = mutableListOf(scheduleReq),
            periodID = UUID.randomUUID().toString()
        )

        eventRes = EventResponseDto(
            id = UUID.randomUUID().toString(),
            name = "Event Name",
            courseName = "Course Name",
            programNames = listOf("Program 1", "Program 2"),
            schedules = listOf(scheduleRes),
            isActive = true,
            isCancelled = false
        )

        user = User(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com",
            image = "",
            isAdmin = true
        )

        principal = Principal().apply {
            id = UUID.randomUUID()
            username = this@EventControllerTest.user.email
            password = "123"
            user = this@EventControllerTest.user
            this.initProperties()
        }
    }

    @Test
    fun `test assignmentAll`() {

//        `when`(eventService.).thenReturn(assignments)

//        val responseEntity = eventController.getAll()

//        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == assignments)
    }




}

