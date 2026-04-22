package ar.edu.unsam.pds.dto.response

import java.time.LocalDate

data class EventResponseDto(
    val id: String,
    val name: String,
    var isApproved: Boolean?,
    var isCancelled: Boolean,
    val courseName: String,
    val programNames: List<String>,
    val schedules: List<ScheduleResponseDto>,
    val type: String,
)

data class EventDetailResponseDto(
    val id: String,

    // ---- datos simples que el form muestra directamente ----
    val name: String,
    val isApproved: Boolean?,
    val isCancelled: Boolean,

    // ---- tipo y detalle ----
    val type: String,
    val details: String,

    // ---- claves que necesita el Autocomplete ----
    val courseID: String?,
    val periodID: String?,

    // ---- periodo personalizado ----
    val customPeriodStart: LocalDate?,
    val customPeriodEnd: LocalDate?,

    // ---- sólo para mostrar (opcional) ----
    val courseName: String,
    val programNames: List<String>,

    // ---- horarios ----
    val schedules: List<ScheduleResponseDto>,
)