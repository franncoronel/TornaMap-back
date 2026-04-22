package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Program
import ar.edu.unsam.pds.repository.CourseRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class CourseService(
    private val courseRepository: CourseRepository,
) {

    fun getAll(): List<Course> = courseRepository.findAllByOrderByEventsPresenceAndName()

    fun searchBy(query: String): List<Course> {
        if (query.isNotBlank()) {
            return courseRepository.searchByNameOrProgramOrProfessorOrEvent(query).distinctBy { it.id }
        }
        return getAll()
    }

    fun findByID(courseID: String?): Course {
        val uuid = UUID.fromString(courseID)
        return courseRepository.findById(uuid).orElseThrow {
            NotFoundException("Curso no encontrado para el uuid suministrado")
        }
    }

    @Transactional
    fun create(course: Course, programs: List<Program>): Course {
        course.addPrograms(programs)
        return courseRepository.save(course)
    }

    @Transactional
    fun update(course: Course, programs: List<Program>): Course {
        val existingCourse = findByID(course.id.toString())

        existingCourse.name = course.name
        existingCourse.description = course.description

        existingCourse.cleanPrograms()
        existingCourse.addPrograms(programs)

        return courseRepository.save(existingCourse)
    }

    @Transactional
    fun delete(courseID: String) {
        val course = findByID(courseID)

        course.events.flatMap { it.schedules }.forEach { schedule ->
            // recorrer una copia para evitar ConcurrentModification
            schedule.assignedUsers.toList().forEach { user ->
                user.scheduleList.remove(schedule)   // ← lado OWNER
            }
            schedule.assignedUsers.clear()           // ← lado INVERSE
        }

        course.programs.toList().forEach { program ->
            program.removeCourse(course)          // mantiene ambos lados
        }

        courseRepository.delete(course)
    }
}
