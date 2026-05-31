/*package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.request.UserRequestUpdateDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserDetailResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.mappers.AssignmentMapper
import ar.edu.unsam.pds.mappers.CourseMapper
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.services.AppUserDetailsService
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.*

class UserServiceTest : BootstrapNBTest() {
    @Mock
    private lateinit var mockRequest: HttpServletRequest

    private lateinit var programService: ProgramService
    private lateinit var userDetailsService: AppUserDetailsService
    private lateinit var userService: UserService

    @BeforeEach
    fun prepareTestData() {
        programService = ProgramService(
            institutionRepository = institutionRepository,
            principalRepository = principalRepository,
            userRepository = userRepository,
            imageService = imageService
        )

        userService = UserService(
            userRepository = userRepository,
            principalRepository = principalRepository,
            programService = programService,

            emailService = emailService,
            storageService = imageService,
            rememberMeServices = rememberMeServices
        )

        userDetailsService = AppUserDetailsService(
            principalRepository = principalRepository
        )
    }

    private fun buildAuthentication(principal: Principal): Authentication {
        return object : Authentication {
            override fun getName() = principal.user?.name!!
            override fun getAuthorities() = principal.authorities
            override fun getCredentials(): Any? = null
            override fun getDetails(): Any? = null
            override fun getPrincipal() = principal
            override fun isAuthenticated() = true
            override fun setAuthenticated(isAuthenticated: Boolean) {}
        }
    }

    @Test
    fun `test load user by username`() {
        val obtainedValue = userDetailsService.loadUserByUsername("adam@email.com")
        val expectedValue = principals[0]

        assertEquals(expectedValue, obtainedValue)
    }

    @Test
    fun `test throw load user by username`() {
        assertThrows<UsernameNotFoundException> {
            userDetailsService.loadUserByUsername("juan_perez@email.com")
        }
    }

    @Test
    fun `test login`() {
        val userForm = LoginForm("adam@email.com", "0")
        val response = MockHttpServletResponse()

        `when`(mockRequest.userPrincipal).thenReturn(
            this.buildAuthentication(principals[0])
        )

        doNothing().`when`(mockRequest).login("adam@email.com", "0")

        val obtainedValue = userService.login(userForm, mockRequest, response)
        val expectedValue = UserDetailResponseDto(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com",
            image = defaultImage,
            id = obtainedValue.id,
            role = ar.edu.unsam.pds.models.Role.ADMIN
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test login - not existed user`() {
        val userForm = LoginForm("adam@email.com", "0")
        val response = MockHttpServletResponse()

        `when`(mockRequest.login("adam@email.com", "0")).thenThrow(ServletException())

        assertThrows<NotFoundException> {
            userService.login(userForm, mockRequest, response)
        }
    }

    @Test
    fun `test login - internal error`() {
        val userForm = LoginForm("adam@email.com", "0")
        val response = MockHttpServletResponse()

        val principal = Principal().apply {
            id = UUID.randomUUID()
            username = "juan perez"
            password = "666"
        }

        `when`(mockRequest.userPrincipal).thenReturn(
            this.buildAuthentication(principal)
        )

        doNothing().`when`(mockRequest).login("adam@email.com", "0")

        assertThrows<InternalServerError> {
            userService.login(userForm, mockRequest, response)
        }
    }

    @Test
    fun `test register a user`() {
        `when`(imageService.defaultImage()).thenReturn(defaultImage)

        val id = userService.register(
            RegisterFormDto(
                name = "juán",
                lastName = "perez",
                email = "juan_perez@email.com",
                password = "123"
            )
        ).id

        val obtainedValue = userService.getUserDetail(id)
        val expectedValue = UserDetailResponseDto(
            name = "juán",
            lastName = "perez",
            email = "juan_perez@email.com",
            image = defaultImage,
            id = id,
            role = null
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw register a user`() {
        assertThrows<InternalServerError> {
            userService.register(
                RegisterFormDto(
                    name = "juán",
                    lastName = "perez",
                    email = "adam@email.com",
                    password = "123"
                )
            )
        }
    }

    @Test
    fun `get all user`() {
        val obtainedValue = userService.getUserAll().toList()
        val expectedValue = users.map {
            UserMapper.buildUserDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular user`() {
        val obtainedValue = userService.getUserDetail(users[0].id.toString())
        val expectedValue = UserMapper.buildUserDetailDto(users[0], null)

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw get a particular user`() {
        assertThrows<NotFoundException> {
            userService.getUserDetail("029ce681-9f90-45e7-af7f-e74a8cfb4b57")
        }
    }

    @Test
    fun `update test for a particular user - with image`() {
        val obtainedValuePre = userService.getUserDetail(users[0].id.toString())
        val expectedValuePre = UserMapper.buildUserDetailDto(users[0], null)

        assertEquals(obtainedValuePre, expectedValuePre)

        val adanUpdate = UserRequestUpdateDto(
            name = "Adan__",
            lastName = "AdanAdan__",
            email = "adan__@email.com",
            id = users[0].id.toString(),
            credits = 200000.0,
            nextClass = null,
            file = mockFile
        )

        `when`(imageService.updatePrivate(
            oldImageName = defaultImage,
            newImageFile = mockFile
        )).thenReturn(mockFileName)

        `when`(emailService.sendCreditsLoadedEmail(
            to = adanUpdate.email,
            credits = adanUpdate.credits,
            userName = adanUpdate.name
        )).then {  }

        userService.updateDetail(
            idUser = users[0].id.toString(),
            userDetail = adanUpdate
        )

        val obtainedValuePos = userService.getUserDetail(users[0].id.toString())
        val expectedValuePos = UserDetailResponseDto(
            name = adanUpdate.name,
            lastName = adanUpdate.lastName,
            email = adanUpdate.email,
            image = mockFileName,
            id = adanUpdate.id!!,
            role = ar.edu.unsam.pds.models.Role.ADMIN
        )

        assertEquals(obtainedValuePos, expectedValuePos)
    }

    @Test
    fun `update test for a particular user - no image`() {
        val obtainedValuePre = userService.getUserDetail(users[0].id.toString())
        val expectedValuePre = UserMapper.buildUserDetailDto(users[0], null)

        assertEquals(obtainedValuePre, expectedValuePre)

        val adanUpdate = UserRequestUpdateDto(
            name = "Adan__",
            lastName = "AdanAdan__",
            email = "adan__@email.com",
            id = users[0].id.toString(),
            credits = 100000.0,
            nextClass = null,
            file = null
        )

        `when`(emailService.sendCreditsLoadedEmail(
            to = adanUpdate.email,
            credits = adanUpdate.credits,
            userName = adanUpdate.name
        )).then {  }

        userService.updateDetail(
            idUser = users[0].id.toString(),
            userDetail = adanUpdate
        )

        val obtainedValuePos = userService.getUserDetail(users[0].id.toString())
        val expectedValuePos = UserDetailResponseDto(
            name = adanUpdate.name,
            lastName = adanUpdate.lastName,
            email = adanUpdate.email,
            image = defaultImage,
            id = adanUpdate.id!!,
            role = ar.edu.unsam.pds.models.Role.ADMIN
        )

        assertEquals(obtainedValuePos, expectedValuePos)
    }

    @Test
    fun `test get subscribed courses`() {
        assertEquals(
            userService.getAssignedCourses(users[0].id.toString()),
            mutableListOf<CourseResponseDto>()
        )

        users[0].addAssignment(assignments[0])
        assignments[0].addSubscribedUser(users[0])
        userRepository.save(users[0])

        assertEquals(
            userService.getAssignedCourses(users[0].id.toString()),
            mutableListOf(CourseMapper.buildCourseDto(assignments[0].course))
        )
    }

    @Test
    fun `test get subscribed`() {
        assertEquals(
            userService.getSubscriptions(users[0].id.toString()),
            mutableListOf<SubscriptionResponseDto>()
        )

        users[0].addAssignment(assignments[0])
        assignments[0].addSubscribedUser(users[0])
        userRepository.save(users[0])

        assertEquals(
            userService.getSubscriptions(users[0].id.toString()),
            mutableListOf(AssignmentMapper.buildSubscriptionDto(assignments[0], programs[0]))
        )
    }

    @Test
    fun `test delete account`() {
        val request = mockRequest
        val response = MockHttpServletResponse()

        `when`(mockRequest.userPrincipal).thenReturn(
            this.buildAuthentication(principals[0])
        )

        userService.deleteAccount(request, response)
    }

    @Test
    fun `test delete account - invalid id`() {
        val request = mockRequest
        val response = MockHttpServletResponse()

        val principal = Principal().apply {
            id = UUID.randomUUID()
            username = "juan perez"
            password = "666"
        }

        `when`(mockRequest.userPrincipal).thenReturn(
            this.buildAuthentication(principal)
        )

        assertThrows<NotFoundException> {
            userService.deleteAccount(request, response)
        }
    }
}*/