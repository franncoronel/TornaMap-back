package ar.edu.unsam.pds.dto.response

import ar.edu.unsam.pds.models.Role

data class UserDetailResponseDto(
    val name: String,
    val lastName: String,
    val email: String,
    val image: String,
    val id: String,
    val role: Role?,
)