package ar.edu.unsam.pds.dto.response

data class StudentEventDto(
    val id: String,
    val name: String,
    val type: String,
    val course: String,
    val programs: String,
    val modality: String,
    val schedules: String
)
