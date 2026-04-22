package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Course
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface CourseRepository : JpaRepository<Course, UUID> {

    @EntityGraph(attributePaths = ["programs", "events", "events.schedules", "events.schedules.assignedUsers", "events.schedules.classroom", "events.schedules.classroom.building"])
    override fun findById(id: UUID): Optional<Course>

    fun findCourseByName(courseName: String): Course?

    @EntityGraph(attributePaths = ["programs", "events", "events.schedules", "events.schedules.assignedUsers"])
    @Query("""
        SELECT  c FROM Course c
        ORDER BY 
            CASE WHEN SIZE(c.events) > 0 THEN 0 ELSE 1 END, 
            c.name ASC
    """)
    fun findAllByOrderByEventsPresenceAndName(): List<Course>


    @EntityGraph(attributePaths = ["programs", "events", "events.schedules", "events.schedules.assignedUsers"])
    @Query("""
        SELECT DISTINCT c, 
       CASE WHEN EXISTS (SELECT 1 FROM Event e WHERE e.course = c) 
           THEN 0 ELSE 1 
               END AS hasEvents 
        FROM Course c 
        WHERE 
            LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) 
            OR EXISTS (
                SELECT 1 FROM c.programs p 
                WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))
            )
             OR EXISTS (
                 SELECT 1 FROM c.events e 
                 JOIN e.schedules s 
                 JOIN s.assignedUsers u 
                 WHERE LOWER(CONCAT(u.name, ' ', u.lastName)) 
                 LIKE LOWER(CONCAT('%', REPLACE(:query, ' ', '%'), '%'))
             )
            OR EXISTS (
                SELECT 1 FROM c.events e
                WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%'))
            )
        ORDER BY hasEvents, c.name
    """)
    fun searchByNameOrProgramOrProfessorOrEvent(@Param("query") query: String): List<Course>

}
