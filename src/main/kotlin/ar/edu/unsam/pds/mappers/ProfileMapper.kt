package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.response.ProfessorReservationDto
import ar.edu.unsam.pds.dto.response.StudentCourseDto
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Schedule

object ProfileMapper {
    fun buildStudentCourseDto(course: Course): StudentCourseDto {
        return StudentCourseDto(
            id = course.id.toString(),
            name = course.name,
            programs = course.programNames().joinToString(" - "),
            events = course.events(),
            professors = course.professors(),
            modality = course.modality(),
            schedules = course.formattedSchedules()
        )
    }

    fun buildProfessorReservationDto(schedule: Schedule): ProfessorReservationDto {
        val event = schedule.event
        val course = event?.course

        // Armamos el string de horarios específico para este schedule
        val scheduleString = "${schedule.translateAndFormatWeekDay()}: ${schedule.startTime} - ${schedule.endTime}"

        return ProfessorReservationDto(
            id = schedule.id.toString(),
            classroomName = schedule.classroom?.getFullName() ?: "Virtual",
            event = event?.name ?: "Evento sin nombre",
            course = course?.name,
            courseData = listOf("Inscriptos: ${course?.students ?: 0}"), // O la data extra que necesites
            schedules = scheduleString
        )
    }
}