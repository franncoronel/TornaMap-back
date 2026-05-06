package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Event
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

@RepositoryRestResource(exported = false)
interface EventRepository : JpaRepository<Event, UUID> {

    @EntityGraph(attributePaths = ["course", "course.programs", "schedules", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    override fun findById(id: UUID): Optional<Event>

    fun findByName(name: String): Optional<Event>

    /**
     *  Búsqueda por código de aula + momento concreto.
     *
     *  Condiciones:
     *  1. El schedule tiene una fecha puntual que coincide con el momento
     *  2. El momento cae dentro del periodo del evento y coincide el día de la semana
     *  3. El momento cae dentro del periodo personalizado y coincide el día de la semana
     *
     *  Se usan LEFT JOIN para course y period porque no todos los eventos los tienen
     *  (ej: CHARLA, SEMINARIO, CONFERENCIA pueden no tener curso ni periodo).
     */
    @Query(
        """
        SELECT DISTINCT e
        FROM Event e
        JOIN FETCH e.schedules s
        JOIN FETCH s.classroom cl
        LEFT JOIN FETCH e.course c
        LEFT JOIN FETCH c.programs p
        LEFT JOIN FETCH cl.building b
        LEFT JOIN FETCH s.assignedUsers u

        WHERE cl.code = :classroomCode
        AND (
            s.date = :moment
            OR (
                e.period IS NOT NULL
                AND :moment BETWEEN e.period.startDate AND e.period.endDate
                AND s.weekDay = :weekDay
            )
            OR (
                e.customPeriodStart IS NOT NULL
                AND :moment BETWEEN e.customPeriodStart AND e.customPeriodEnd
                AND s.weekDay = :weekDay
            )
        )
        ORDER BY s.startTime ASC
        """
    )
    fun findEventsByClassroomAndDate(
        @Param("classroomCode") classroomCode: String,
        @Param("moment")        moment: LocalDate,
        @Param("weekDay")       weekDay: DayOfWeek
    ): List<Event>

    @EntityGraph(attributePaths = ["schedules", "course", "course.programs", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    override fun findAll(): List<Event>

    fun existsByPeriodId(periodId: UUID): Boolean

    @EntityGraph(attributePaths = ["schedules", "course", "course.programs", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    fun findByIsApprovedIsNull(): List<Event>

}