package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.CreateReservationDto
import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.request.UserRequestUpdateDto
import ar.edu.unsam.pds.dto.response.ProfessorReservationDto
import ar.edu.unsam.pds.dto.response.StudentCourseDto
import ar.edu.unsam.pds.dto.response.StudentEventDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.models.enums.EventType
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.mappers.ProfileMapper
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.models.Event
import ar.edu.unsam.pds.models.Schedule
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.ClassroomRepository
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.EventRepository
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import ar.edu.unsam.pds.tools.clearCookies
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalTime
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val principalRepository: PrincipalRepository,
    private val storageService: StorageService,
    private val rememberMeServices: TokenBasedRememberMeServices,
    private val courseRepository: CourseRepository,
    private val eventRepository: EventRepository,
    private val classroomRepository: ClassroomRepository,
) {

    fun getAll(): MutableList<User> {
        return userRepository.findAllByOrderByNameAsc()
    }

    fun login(user: LoginForm, request: HttpServletRequest, response: HttpServletResponse): UserResponseDto {
        try {
            request.login(user.email, user.password)
        } catch (e: ServletException) {
            throw NotFoundException("Usuario y/o contraseña invalidos.")
        }

        val auth: Authentication = request.userPrincipal as Authentication

        if (user.rememberMe) {
            rememberMeServices.loginSuccess(request, response, auth)
        }

        val loggedUser = (auth.principal as Principal).getUser()
        return UserMapper.buildUserDto(loggedUser)   // ← devuelve el UserResponseDto con role
    }

    @Transactional
    fun register(form: RegisterFormDto): UUID {
        if (principalRepository.findUserByEmail(form.email).isPresent) {
            throw InternalServerError("El correo ya está en uso. Si elimino su cuenta y quiere recuperarla dirijase a @pirulo")
        }
        val encryptedPassword = encryptPassword(form.password)

        val newUser = User(
            name = form.name,
            lastName = form.lastName,
            email = form.email,
            image = storageService.defaultImage(),
            role = form.role
        )
        userRepository.save(newUser)

        val principal = Principal().apply {
            username = form.email
            password = encryptedPassword
            initProperties()
            user = newUser
        }
        principalRepository.save(principal)

        return newUser.id
    }

    private fun encryptPassword(password: String): String {
        val encoder = BCryptPasswordEncoder()
        return encoder.encode(password)
    }


    @Transactional
    fun updateDetail(idUser: String, userDetail: UserRequestUpdateDto): User {
        val user = findUserById(idUser)

        if (userDetail.image != null) {
            val imageName = storageService.updatePrivate(user.image, userDetail.image)

            user.image = imageName
        }

        user.name = userDetail.name
        user.lastName = userDetail.lastName
        user.email = userDetail.email

        userRepository.save(user)
        return user
    }

    private fun findUserById(idUser: String): User {
        val uuid = UUID.fromString(idUser)
        return userRepository.findById(uuid).orElseThrow {
            NotFoundException("Usuario no encontrado para el uuid suministrado")
        }
    }

    @Transactional
    fun deleteAccount(request: HttpServletRequest, response: HttpServletResponse) {
        val auth: Authentication = request.userPrincipal as Authentication
        val id = (auth.principal as Principal).id

        principalRepository.findById(id).map { principal ->

            principalRepository.deleteWithDestructionOfAllSessions(principal)
            storageService.deletePrivate(principal.getUser().image)
            clearCookies(request, response)

        }.orElseThrow {
            NotFoundException("Usuario no encontrado para el uuid suministrado")
        }
    }

    //--- Para Profile de STUDENT---
    @Transactional
    fun getMyCourses(request: HttpServletRequest): List<StudentCourseDto> {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()
        val managedUser = userRepository.findById(principalUser.id).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }
        return managedUser.courses.map { ProfileMapper.buildStudentCourseDto(it) }
    }

    @Transactional
    fun subscribeToCourse(request: HttpServletRequest, courseId: String) {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()

        val courseIdUUID = UUID.fromString(courseId)

        if (userRepository.countUserCourse(principalUser.id, courseIdUUID) > 0) {
            throw InternalServerError("Ya estás suscripto a esta materia")
        }

        userRepository.addCourseToUser(principalUser.id, courseIdUUID)
    }


    @Transactional
    fun unsubscribeFromCourse(request: HttpServletRequest, courseId: String) {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()

        val courseIdUUID = UUID.fromString(courseId)

        userRepository.removeCourseFromUser(principalUser.id, courseIdUUID)
    }

    //--- Eventos suscriptos por el STUDENT (charlas, seminarios, parciales, etc.; no cursadas) ---
    @Transactional
    fun getMyEvents(request: HttpServletRequest): List<StudentEventDto> {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()
        val managedUser = userRepository.findById(principalUser.id).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }
        return managedUser.events.map { ProfileMapper.buildStudentEventDto(it) }
    }

    @Transactional
    fun subscribeToEvent(request: HttpServletRequest, eventId: String) {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()

        val eventIdUUID = UUID.fromString(eventId)
        val event = eventRepository.findById(eventIdUUID).orElseThrow {
            NotFoundException("Evento no encontrado")
        }

        if (event.type == EventType.CURSADA) {
            throw ValidationException("No podés suscribirte a una cursada; suscribite a la materia correspondiente.")
        }
        if (event.isApproved != true) {
            throw ValidationException("Sólo podés suscribirte a eventos aprobados.")
        }
        if (userRepository.countUserEvent(principalUser.id, eventIdUUID) > 0) {
            throw InternalServerError("Ya estás suscripto a este evento")
        }

        userRepository.addEventToUser(principalUser.id, eventIdUUID)
    }

    @Transactional
    fun unsubscribeFromEvent(request: HttpServletRequest, eventId: String) {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()

        val eventIdUUID = UUID.fromString(eventId)

        userRepository.removeEventFromUser(principalUser.id, eventIdUUID)
    }

    //--- Para Profile de PROFESSOR---
    @Transactional
    fun createReservation(request: HttpServletRequest, dto: CreateReservationDto) {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()
        // Traemos el usuario "managed" desde la BD: `principalUser` viene de la sesión (detached)
        // y es el lado dueño del join app_user_schedule, así que el vínculo no se persistiría.
        val managedUser = userRepository.findById(principalUser.id).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }

        // 1. Buscamos el aula
        val classroomIdUUID = UUID.fromString(dto.classroomId)
        val classroom = classroomRepository.findById(classroomIdUUID).orElseThrow {
            NotFoundException("Aula no encontrada")
        }

        // 2. Validación de negocio (según tu captura: reservas permitidas solo de 06:00 a 22:00)
        if (dto.startTime.isBefore(LocalTime.of(6, 0)) || dto.endTime.isAfter(LocalTime.of(22, 0))) {
            throw ValidationException("Las reservas sólo se permiten entre las 06:00 y las 22:00 horas.")
        }

        // (Opcional) Acá también podrías llamar al repositorio para validar si el aula
        // ya está ocupada en ese rango horario usando `findEventsByClassroomAndDate`.

        // 2.b Si viene asignatura (Parcial/Final), la buscamos para asociarla
        val course = dto.courseID?.takeIf { it.isNotBlank() }?.let {
            courseRepository.findById(UUID.fromString(it)).orElseThrow {
                NotFoundException("Asignatura no encontrada")
            }
        }

        // 3. Instanciamos el Evento (usando tu constructor exacto)
        val newEvent = Event(
            name = dto.title,
            isApproved = null, // Pendiente para que el Admin lo apruebe (findByIsApprovedIsNull)
            course = course, // Solo para Parcial/Final
            type = dto.eventType, // Viene del select del front
            details = dto.details ?: "",
            customPeriodStart = dto.date, // Al ser reserva puntual, empieza y termina el mismo día
            customPeriodEnd = dto.date
        )

        // 4. Creamos el Horario (Schedule)
        val schedule = Schedule(
            date = dto.date,
            startTime = dto.startTime,
            endTime = dto.endTime,
            weekDay = dto.date.dayOfWeek,
            isVirtual = false
        ).apply {
            this.classroom = classroom // Asignamos el aula que está en el cuerpo de la clase
        }

        // 5. Armamos las relaciones bidireccionales usando los métodos de tu dominio
        newEvent.addSchedule(schedule)
        newEvent.addUserToSchedule(schedule, managedUser) // Esto vincula al profesor a la reserva

        // 6. Guardamos el evento (CascadeType.ALL se encarga de guardar el Schedule)
        eventRepository.save(newEvent)
        // 7. Persistimos el lado dueño de la relación (app_user_schedule)
        userRepository.save(managedUser)
    }

    @Transactional
    fun getMyReservations(request: HttpServletRequest, isApproved: Boolean?): List<ProfessorReservationDto> {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()
        // Re-fetch managed: la sesión trae un user detached (con el scheduleList del login).
        val managedUser = userRepository.findById(principalUser.id).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }

        // O mostramos reservas aprobadas, o mostramos reservas pendientes.
        return managedUser.scheduleList
            .filter { schedule ->
                val approvalState = schedule.event?.isApproved
                isApproved == null ||
                        (isApproved && approvalState == true) ||
                        (!isApproved && approvalState == null)
            }
            .map { ProfileMapper.buildProfessorReservationDto(it) }
    }

    @Transactional
    fun cancelReservation(request: HttpServletRequest, scheduleId: String) {
        val auth = request.userPrincipal as Authentication
        val principalUser = (auth.principal as Principal).getUser()
        val managedUser = userRepository.findById(principalUser.id).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }

        val scheduleToRemove = managedUser.scheduleList.find { it.id.toString() == scheduleId }
            ?: throw NotFoundException("Reserva no encontrada")

        managedUser.scheduleList.remove(scheduleToRemove)
        userRepository.save(managedUser)
    }


}