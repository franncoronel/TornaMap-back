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

    @EntityGraph(attributePaths = ["course", "course.programs", "schedules","schedules.classroom","schedules.classroom.building", "schedules.assignedUsers"])
    override fun findById(id: UUID): Optional<Event>

    fun findByName(name: String): Optional<Event>

    /**
     *  Búsqueda por código de aula + momento concreto.
     *
     *  Parámetros:
     *  @param classroomCode
     *  @param moment
     *  @param weekDay
     */
    @Query(
        """
        SELECT DISTINCT e
        FROM Event e
        JOIN FETCH e.schedules s
        JOIN FETCH e.course c
        JOIN FETCH s.classroom cl
        LEFT JOIN FETCH c.programs p
        LEFT JOIN FETCH cl.building b
        LEFT JOIN FETCH s.assignedUsers u

        WHERE cl.code = :classroomCode
        AND (
            s.date = :moment
            OR (
                :moment BETWEEN e.period.startDate AND e.period.endDate
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

    @EntityGraph( attributePaths = ["schedules", "course", "course.programs", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    override fun findAll(): List<Event>

    fun existsByPeriodId(periodId: UUID): Boolean

    //lista de eventos pendientes de aprobacion
    fun findByIsApprovedIsNull(): List<Event>

}