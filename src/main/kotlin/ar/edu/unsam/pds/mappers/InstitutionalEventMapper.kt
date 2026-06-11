package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.response.InstitutionalEventResponseDto
import ar.edu.unsam.pds.models.Event

object InstitutionalEventMapper {

    fun buildDto(event: Event): InstitutionalEventResponseDto {
        val schedule = event.schedules.firstOrNull()

        return InstitutionalEventResponseDto(
            id = event.id.toString(),
            name = event.name,
            type = event.type.name,
            details = event.details,
            startDate = schedule?.date,
            startTime = schedule?.startTime?.toString(),
            endTime = schedule?.endTime?.toString(),
            location = if (schedule?.isVirtual == true)
                "Virtual"
            else
                schedule?.classroom?.let {
                    "${it.name} - ${it.building?.name ?: ""}"
                },
            isVirtual = schedule?.isVirtual ?: false
        )
    }
}