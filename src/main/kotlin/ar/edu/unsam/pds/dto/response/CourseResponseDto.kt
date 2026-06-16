package ar.edu.unsam.pds.dto.response

data class CourseResponseDto(
    val id: String,
    val name: String,
    val programs: List<String>,
    val events: List<EventResponseDto>
)

data class CourseListResponseDto(
    val id: String,
    val name: String,
    val programs: String,
    val events: String,
    val professors: String,
    val modality: String,
    val schedules: String
)

data class CourseAiDto(
    val id: String,
    val name: String,
    val students: Int
)