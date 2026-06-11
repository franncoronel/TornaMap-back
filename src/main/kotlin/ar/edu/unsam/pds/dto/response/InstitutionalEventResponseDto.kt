package ar.edu.unsam.pds.dto.response

import java.time.LocalDate


data class InstitutionalEventResponseDto(
    val id: String,
    val name: String,
    val type: String,
    val details: String,
    val startDate: LocalDate?,
    val startTime: String?,
    val endTime: String?,
    val location: String?,
    val isVirtual: Boolean,
    val professors: List<String>,
)

data class InstitutionalEventsResponseDto(
    val current: List<InstitutionalEventResponseDto>,
    val pendingToday: List<InstitutionalEventResponseDto>,
    val finished: List<InstitutionalEventResponseDto>,
    val upcoming: List<InstitutionalEventResponseDto>
)