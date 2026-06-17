package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.request.UserRequestUpdateDto
import ar.edu.unsam.pds.dto.response.CustomResponse
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
class UserController : UUIDValid() {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("")
    @Operation(summary = "Get all users")
    fun getAll(): ResponseEntity<CustomResponse> {
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Usuarios obtenidos con exito",
                data = userService.getAll().map { UserMapper.buildUserDto(it) }
            )
        )
    }

    @GetMapping("profile")
    @Operation(summary = "Get user profile")
    fun getProfile(
        request: HttpServletRequest
    ): ResponseEntity<CustomResponse> {

        val auth = request.userPrincipal as? Authentication
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        val principalUser = (auth.principal as Principal).getUser()
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Perfil de usuario obtenido con exito",
                data = UserMapper.buildUserDto(principalUser)
            )
        )
    }

    @PostMapping("login")
    fun login(
        @RequestBody @Valid user: LoginForm,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ResponseEntity<CustomResponse> {
        return ResponseEntity.status(201).body(
            CustomResponse(
                message = "Login exitoso",
                data = userService.login(user, request, response)
            )
        )
    }

    @PostMapping("logout")
    fun logout(
        request: HttpServletRequest
    ): ResponseEntity<CustomResponse> {
        return ResponseEntity.status(201).body(
            CustomResponse(
                message = "Logout exitosos",
                data = request.logout()
            )
        )
    }

    @PostMapping("register")
    @Operation(summary = "Register a new user")
    fun register(
        @RequestBody @Valid form: RegisterFormDto
    ): ResponseEntity<CustomResponse> {
        return ResponseEntity.status(201).body(
            CustomResponse(
                message = "Usuario registrado con exito",
                data = userService.register(form)
            )
        )
    }

    @PatchMapping(value = ["/{idUser}"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "Update a user's details")
    fun updateDetail(
        @PathVariable idUser: String,
        @ModelAttribute @Valid user: UserRequestUpdateDto
    ): ResponseEntity<CustomResponse> {
        this.validatedUUID(idUser)
        return ResponseEntity.status(201).body(
            CustomResponse(
                message = "Usuario actualizado con exito",
                data = UserMapper.buildUserDto(userService.updateDetail(idUser, user))
            )
        )
    }

    @DeleteMapping("")
    @Operation(summary = "Delete account")
    fun deleteAccount(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ResponseEntity<CustomResponse> {
        return ResponseEntity.status(200).body(
            CustomResponse(
                message = "Usuario eliminado con exito",
                data = userService.deleteAccount(request, response)
            )
        )
    }

    // --- Endpoints de Perfil (STUDENT) ---
    @GetMapping("me/courses")
    @Operation(summary = "Get subscribed courses for logged student")
    fun getMyCourses(request: HttpServletRequest): ResponseEntity<CustomResponse> {
        return ResponseEntity.ok(
            CustomResponse(
                message = "Materias obtenidas con éxito",
                data = userService.getMyCourses(request)
            )
        )
    }

    @PostMapping("me/courses/{courseId}")
    @Operation(summary = "Subscribe to a course")
    fun subscribeToCourse(
        @PathVariable courseId: String,
        request: HttpServletRequest
    ): ResponseEntity<CustomResponse> {
        this.validatedUUID(courseId)
        userService.subscribeToCourse(request, courseId)
        return ResponseEntity.status(201).body(
            CustomResponse(message = "Suscripción a materia exitosa", data = null)
        )
    }

    @DeleteMapping("me/courses/{courseId}")
    @Operation(summary = "Unsubscribe from a course")
    fun unsubscribeFromCourse(
        @PathVariable courseId: String,
        request: HttpServletRequest
    ): ResponseEntity<CustomResponse> {
        userService.unsubscribeFromCourse(request, courseId)
        return ResponseEntity.ok(
            CustomResponse(message = "Desuscrito con éxito", data = null)
        )
    }

    // --- Endpoints de Perfil (PROFESSOR) ---
    @GetMapping("me/reservations")
    @Operation(summary = "Get reservations for logged professor")
    fun getMyReservations(request: HttpServletRequest): ResponseEntity<CustomResponse> {
        return ResponseEntity.ok(
            CustomResponse(
                message = "Reservas obtenidas con éxito",
                data = userService.getMyReservations(request)
            )
        )
    }

    @DeleteMapping("me/reservations/{scheduleId}")
    @Operation(summary = "Cancel a reservation")
    fun cancelReservation(
        @PathVariable scheduleId: String,
        request: HttpServletRequest
    ): ResponseEntity<CustomResponse> {
        userService.cancelReservation(request, scheduleId)
        return ResponseEntity.ok(
            CustomResponse(message = "Reserva cancelada con éxito", data = null)
        )
    }

}
