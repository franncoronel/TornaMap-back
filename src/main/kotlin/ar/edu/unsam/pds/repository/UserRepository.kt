package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>

    fun findAllByOrderByNameAsc(): MutableList<User>

    @Modifying
    @Query(
        value = "DELETE FROM app_user_course WHERE user_id = :userId AND course_id = :courseId",
        nativeQuery = true
    )
    fun removeCourseFromUser(
        @Param("userId") userId: UUID,
        @Param("courseId") courseId: UUID
    )

    @Modifying
    @Query(
        value = "INSERT INTO app_user_course (user_id, course_id) VALUES (:userId, :courseId)",
        nativeQuery = true
    )
    fun addCourseToUser(
        @Param("userId") userId: UUID,
        @Param("courseId") courseId: UUID
    )

    @Query(
        value = "SELECT COUNT(*) FROM app_user_course WHERE user_id = :userId AND course_id = :courseId",
        nativeQuery = true
    )
    fun countUserCourse(
        @Param("userId") userId: UUID,
        @Param("courseId") courseId: UUID
    ): Long

    @Modifying
    @Query(
        value = "DELETE FROM app_user_event WHERE user_id = :userId AND event_id = :eventId",
        nativeQuery = true
    )
    fun removeEventFromUser(
        @Param("userId") userId: UUID,
        @Param("eventId") eventId: UUID
    )

    @Modifying
    @Query(
        value = "INSERT INTO app_user_event (user_id, event_id) VALUES (:userId, :eventId)",
        nativeQuery = true
    )
    fun addEventToUser(
        @Param("userId") userId: UUID,
        @Param("eventId") eventId: UUID
    )

    @Query(
        value = "SELECT COUNT(*) FROM app_user_event WHERE user_id = :userId AND event_id = :eventId",
        nativeQuery = true
    )
    fun countUserEvent(
        @Param("userId") userId: UUID,
        @Param("eventId") eventId: UUID
    ): Long


}
