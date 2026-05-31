package ar.edu.unsam.pds

import ar.edu.unsam.pds.models.*
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import org.junit.jupiter.api.BeforeEach
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

open class BootstrapBasicTest {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(10)

    var principals = mutableListOf<Principal>()
    var users = mutableListOf<User>()
    var schedules = mutableListOf<Schedule>()
    var events = mutableListOf<Event>()
    var courses = mutableListOf<Course>()
    var programs = mutableListOf<Program>()

    val defaultImage = "https://mock.pirulo/media/private/default.png"

    private fun encode(clave: String): String {
        return passwordEncoder.encode(clave)
    }

    @BeforeEach
    fun setUp() {
        users.add(
            User(
                name = "Adam",
                lastName = "AdamAdam",
                email = "adam@email.com",
                image = defaultImage,
                role = Role.ADMIN
            )
        )

        users.add(
            User(
                name = "Eve",
                lastName = "Eve",
                email = "eve@email.com",
                image = defaultImage,
                role = Role.STUDENT
            )
        )

        users.add(
            User(
                name = "Boniface",
                lastName = "Gomez",
                email = "boniface@email.com",
                image = defaultImage,
                role = Role.PROFESSOR
            )
        )

        users.forEachIndexed { i, it ->
            principals.add(Principal().apply {
                username = it.email
                password = encode(i.toString())
                user = it
                this.initProperties()
            })
        }

        schedules.add(
            Schedule(
                days = listOf(DayOfWeek.MONDAY),
                startTime = LocalTime.of(19, 0),
                endTime = LocalTime.of(20, 0),
                startDate = LocalDate.now().minusMonths(1),
                endDate = LocalDate.now().plusMonths(5),
                recurrenceWeeks = RecurrenceWeeks.WEEKLY,
            )
        )

        schedules.add(
            Schedule(
                days = listOf(DayOfWeek.TUESDAY),
                startTime = LocalTime.of(19, 0),
                endTime = LocalTime.of(21, 0),
                startDate = LocalDate.now().minusMonths(1),
                endDate = LocalDate.now().plusMonths(5),
                recurrenceWeeks = RecurrenceWeeks.BIWEEKLY,
            )
        )

        events.add(
            Event(
                quotas = 100,
                isApproved = true,
                price = 100.0,
                schedule = schedules[0]
            )
        )

        events.add(
            Event(
                quotas = 100,
                isApproved = true,
                price = 100.0,
                schedule = schedules[1]
            )
        )

        courses.add(Course(
            name = "classic dance",
            description = "classical dance course",
            category = "dance",
            image = ""
        ).apply {
            addEvent(this@BootstrapBasicTest.events[0])
        })

        courses.add(Course(
            name = "modern dance",
            description = "modern dance course",
            category = "dance",
            image = ""
        ).apply {
            addEvent(this@BootstrapBasicTest.events[1])
        })

        courses.add(
            Course(
                name = "yoga",
                description = "yoga course",
                category = "yoga_category",
                image = ""
            )
        )

        programs.add(Program(
            name = "Enchanted Dance",
            description = "dance institution",
            category = "dance_category",
            image = ""
        ).apply {
            addCourse(this@BootstrapBasicTest.courses[0])
            addCourse(this@BootstrapBasicTest.courses[1])
            addAdmin(principals[0].getUser())
        })

        programs.add(Program(
            name = "The Kingdom of Calculations",
            description = "mathematics institution",
            category = "mathematics_category",
            image = "",
        ))

        programs.add(
            Program(
                name = "Serenity and Postures",
                description = "yoga institution",
                category = "yoga_category",
                image = ""
            )
        )
    }
}