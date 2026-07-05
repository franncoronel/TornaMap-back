package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Event
import ar.edu.unsam.pds.models.enums.EventType
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
        LEFT JOIN e.period per

        WHERE cl.code = :classroomCode
        AND e.isApproved = true
        AND (
            s.date = :moment
            OR (
                per IS NOT NULL
                AND :moment BETWEEN per.startDate AND per.endDate
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

    @EntityGraph(attributePaths = ["course"])
    @Query("SELECT e FROM Event e WHERE e.period.id = :periodId AND e.course IS NOT NULL")
    fun findByPeriodIdWithCourse(@Param("periodId") periodId: UUID): List<Event>

    // Eventos institucionales (sin curso asociado)

    @EntityGraph(attributePaths = ["schedules", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    @Query("SELECT e FROM Event e WHERE e.course IS NULL AND e.type IN :types AND e.isApproved = true")
    fun findStandaloneByTypes(@Param("types") types: List<EventType>): List<Event>

    // Búsqueda de eventos institucionales por nombre

    @EntityGraph(attributePaths = ["schedules", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    @Query("""
        SELECT e FROM Event e
        WHERE e.course IS NULL
          AND e.type IN :types
          AND e.isApproved = true
          AND LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    fun searchStandaloneByName(
        @Param("types") types: List<EventType>,
        @Param("query") query: String
    ): List<Event>
    @EntityGraph(attributePaths = ["course", "course.programs", "schedules", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    @Query("""
        SELECT DISTINCT e
        FROM Event e
        JOIN e.schedules s
        WHERE e.isApproved = true
          AND e.type IN ('CHARLA', 'SEMINARIO', 'CONFERENCIA')
          AND s.date = CURRENT_DATE
          AND s.startTime <= CURRENT_TIME
          AND s.endTime >= CURRENT_TIME
    """)
    fun findInstitutionalEventsInProgressToday(): List<Event>   //Eventos  de hoy que ya empezaron y todavía no terminaron

    @EntityGraph(attributePaths = ["course", "course.programs", "schedules", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    @Query("""
        SELECT DISTINCT e
        FROM Event e
        JOIN e.schedules s
        WHERE e.isApproved = true
          AND e.type IN ('CHARLA', 'SEMINARIO', 'CONFERENCIA')
          AND s.date = CURRENT_DATE
          AND s.startTime > CURRENT_TIME
    """)
    fun findTodayNotStartedInstitutionalEvents(): List<Event>  //Eventos sin iniciar

    @EntityGraph(attributePaths = ["course", "course.programs", "schedules", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    @Query("""
        SELECT DISTINCT e
        FROM Event e
        JOIN e.schedules s
        WHERE e.isApproved = true
          AND e.type IN ('CHARLA', 'SEMINARIO', 'CONFERENCIA')
          AND s.date = CURRENT_DATE
          AND s.endTime < CURRENT_TIME
    """)
    fun findFinishedTodayInstitutionalEvents(): List<Event> //Eventos que ya terminaron

    @EntityGraph(attributePaths = ["course", "course.programs", "schedules", "schedules.classroom", "schedules.classroom.building", "schedules.assignedUsers"])
    @Query("""
        SELECT DISTINCT e
        FROM Event e
        JOIN e.schedules s
        WHERE e.isApproved = true
          AND e.type IN ('CHARLA', 'SEMINARIO', 'CONFERENCIA')
          AND s.date > CURRENT_DATE
          AND s.date <= :endDate
    """)
    fun findInstitutionalEventsForNextDays(@Param("endDate") endDate: LocalDate): List<Event>  //Eventos que ocurren durante la semana
}