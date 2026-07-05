package ar.edu.unsam.pds.dto.response

data class ProfessorReservationDto (
    val id: String,
    val classroomName: String,
    val event: String,
    val course: String?,
    val courseData: List<String>,
    val schedules: String
)