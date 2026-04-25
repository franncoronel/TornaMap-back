package ar.edu.unsam.pds.models

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*

@Entity @Table(name = "APP_SCHEDULE")
class Schedule(
    @JsonFormat(pattern = "HH:mm")
    var startTime: LocalTime,
    @JsonFormat(pattern = "HH:mm")
    var endTime: LocalTime,
    var weekDay: DayOfWeek?,
    var date: LocalDate?,
    var isVirtual: Boolean,


    ) : Timestamp(), Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "scheduleList")
    val assignedUsers = mutableSetOf<User>()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    lateinit var event: Event

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id", nullable = true)
    var classroom: Classroom? = null

    fun isBeforeEndDate(enteredDate: LocalDate): Boolean {
        if (date == null) {
            throw IllegalStateException("No se puede verificar la fecha porque 'date' es nulo en este Schedule")
        }
        return enteredDate.isBefore(date) || enteredDate.isEqual(date)
    }

    fun getUserNames(): List<String> {
        return assignedUsers.map { it.fullName() }
    }

    fun assignUserToSchedule(user: User, schedule: Schedule) {
        this.assignedUsers.add(user)
        user.scheduleList.add(schedule)
    }

    fun translateAndFormatWeekDay(): String {
        val translatedDay = weekDay?.getDisplayName(TextStyle.FULL, Locale("es")) ?: ""
        return translatedDay.uppercase()
    }

}
