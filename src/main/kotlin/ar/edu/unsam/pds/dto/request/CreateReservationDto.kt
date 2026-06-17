package ar.edu.unsam.pds.dto.request

import ar.edu.unsam.pds.models.enums.EventType
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalTime

data class CreateReservationDto(
    @field:NotBlank(message = "El ID del aula es obligatorio")
    val classroomId: String,

    @field:NotBlank(message = "El nombre del evento o reserva es obligatorio")
    val title: String,

    @field:NotNull(message = "La fecha es obligatoria")
    val date: LocalDate,

    @field:NotNull(message = "La hora de inicio es obligatoria")
    val startTime: LocalTime,

    @field:NotNull(message = "La hora de fin es obligatoria")
    val endTime: LocalTime,

    @field:NotNull(message = "El tipo de evento es obligatorio")
    val eventType: EventType // Parcial, Charla, Seminario, etc.
)