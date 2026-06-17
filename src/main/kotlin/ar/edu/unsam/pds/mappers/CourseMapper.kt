package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.request.CourseRequestDto
import ar.edu.unsam.pds.dto.response.*
import ar.edu.unsam.pds.models.Course

object CourseMapper {

    fun buildCourseDto(course: Course): CourseResponseDto {
        return CourseResponseDto(
            id = course.id.toString(),
            name = course.name,
            programs = course.programNames(),
            events = course.events.map { EventMapper.buildEventDto(it) }
        )
    }

    fun buildCourseListDto(course: Course): CourseListResponseDto {
        return CourseListResponseDto(
            id = course.id.toString(),
            name = course.name,
            programs = course.programNames().joinToString(", "),
            events = course.events(),
            professors = course.professors(),
            modality= course.modality(),
            schedules = course.formattedSchedules()
        )
    }

    fun buildCourseDetailDto(course: Course): CourseDetailResponseDto {
        val events = if(course.events.isEmpty()) mutableSetOf() else course.events.filter{it.isApproved == true}.map { EventMapper.buildEventDto(it) }.toMutableSet()

        return CourseDetailResponseDto(
            id = course.id.toString(),
            name = course.name,
            description = course.description,
            programs = course.programNames(),
            events = events
        )
    }

    fun buildCourse(course: CourseRequestDto): Course {

        return Course(
            name = course.name,
            description = course.description
        ).apply {
            id = course.id?.let { java.util.UUID.fromString(it) } ?: java.util.UUID.randomUUID()
        }
    }

    fun buildCourseAiDto(course: Course): CourseAiDto {
        return CourseAiDto(
            id = course.id.toString(),
            name = course.name,
            students = course.students
        )
    }
}