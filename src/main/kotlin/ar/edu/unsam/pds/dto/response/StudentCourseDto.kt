package ar.edu.unsam.pds.dto.response

data class StudentCourseDto(
    val id: String,
    val name: String,
    val programs: String,
    val events: String,
    val professors: String,
    val modality: String,
    val schedules: String
)