package ar.edu.unsam.pds.dto.request

import ar.edu.unsam.pds.models.Role
import jakarta.validation.constraints.*

data class RegisterFormDto(
    @field: NotBlank(message = "El campo nombre no puede estar vacío")
    @field: NotNull(message = "El campo nombre no puede ser nulo")
    @field: Size(max = 250, message = "El campo nombre debe tener como máximo 250 caracteres")
    val name: String = "",

    @field: NotBlank(message = "El campo apellido no puede estar vacío")
    @field: NotNull(message = "El campo apellido no puede ser nulo")
    @field: Size(max = 250, message = "El campo apellido debe tener como máximo 250 caracteres")
    val lastName: String = "",

    @field: NotEmpty(message = "El campo email es requerido")
    @field: NotBlank(message = "El campo email no debe estar en blanco")
    @field: Email(message = "Debe ser una dirección de correo electrónico con formato correcto")
    val email: String = "",

    @field: NotBlank(message = "El campo contraseña no puede estar vacío")
    @field: NotNull(message = "El campo contraseña no puede ser nulo")
    @field: Size(max = 250, message = "El campo contraseña debe tener como máximo 250 caracteres")
    val password: String = "",

    val role: Role? = null,
)