package ar.edu.unsam.pds.dto.response

data class EventResponseDto(
    val id: String,
    val name: String,
    var isApproved: Boolean?,
    var isCancelled: Boolean,
    val courseName: String,
    val programNames: List<String>,
    val schedules: List<ScheduleResponseDto>,
)

data class EventDetailResponseDto(
    val id: String,

    // ---- datos simples que el form muestra directamente ----
    val name: String,
    val isApproved: Boolean?,
    val isCancelled: Boolean,

    // ---- claves que necesita el Autocomplete ----
    val courseID: String,
    val periodID: String,             // puede ser null

    // ---- sólo para mostrar (opcional) ----
    val courseName: String,
    val programNames: List<String>,

    // ---- horarios ----
    val schedules: List<ScheduleResponseDto>
)