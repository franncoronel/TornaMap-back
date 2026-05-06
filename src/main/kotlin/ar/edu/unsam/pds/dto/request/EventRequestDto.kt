package ar.edu.unsam.pds.dto.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class EventRequestDto(

    val id: String?,

    val isApproved: Boolean,

    val isCancelled: Boolean,

    // Ya no es @NotNull ni @Pattern: solo aplica para CURSADA
    val periodID: String?,

    // Ya no es @NotNull ni @Pattern: no aplica para CHARLA/SEMINARIO/CONFERENCIA
    val courseID: String?,

    @field:NotNull(message = "El nombre no debe ser nulo")
    @field:NotBlank(message = "El nombre no puede estar vacío")
    val name: String,

    @field:Valid
    val schedules: MutableList<ScheduleRequestDto>,

    @field:NotNull(message = "El tipo no debe ser nulo")
    @field:NotBlank(message = "El tipo no puede estar vacío")
    val type: String,

    val details: String?,

    val customPeriodStart: LocalDate?,

    val customPeriodEnd: LocalDate?
)
