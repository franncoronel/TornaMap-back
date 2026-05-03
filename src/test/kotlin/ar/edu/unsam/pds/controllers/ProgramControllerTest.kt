package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.ProgramRequestDto
import ar.edu.unsam.pds.mappers.InstitutionMapper
import ar.edu.unsam.pds.models.Program
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.ProgramService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile
import java.util.*

@ExtendWith(MockitoExtension::class)
class ProgramControllerTest {
    @Mock
    private lateinit var programService: ProgramService
    private lateinit var programController: ProgramController

    private lateinit var program: Program
    private lateinit var principal: Principal
    private lateinit var user: User
    private lateinit var uuid: String

    private lateinit var fileImg: MockMultipartFile

    @BeforeEach
    fun setUp() {
        programController = ProgramController()
        programController.programService = programService

        program = Program(
            name = "name",
            description = "description",
            category = "category",
            image = "image"
        ).apply {
            id = UUID.randomUUID()
        }

        uuid = program.id.toString()

        user = User(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com",
            image = "",
            credits = 100000.0,
            isAdmin = true
        )

        principal = Principal().apply {
            id = UUID.randomUUID()
            username = this@ProgramControllerTest.user.email
            password = "123"
            user = this@ProgramControllerTest.user
            this.initProperties()
        }

        fileImg = MockMultipartFile(
            "file",
            "filename.jpg",
            "text/plain",
            "some content".toByteArray()
        )
    }

    @Test
    fun `test get all institutions - no query`() {
        val institutions = listOf(InstitutionMapper.buildInstitutionDto(program))

        `when`(programService.getAll("")).thenReturn(institutions)

        val responseEntity = programController.getAll(null)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institutions)
    }

    @Test
    fun `test get all institutions - query`() {
        val institutions = listOf(InstitutionMapper.buildInstitutionDto(program))

        `when`(programService.getAll("query")).thenReturn(institutions)

        val responseEntity0 = programController.getAll("query")

        assert(responseEntity0.statusCode == HttpStatus.OK)
        assert(responseEntity0.body == institutions)
    }

    @Test
    fun `test get all institutions by principal - no query`() {
        val institutions = listOf(InstitutionMapper.buildInstitutionDto(program))

        `when`(programService.getAllByPrincipal("", principal)).thenReturn(institutions)

        val responseEntity = programController.getAllByPrincipal(null, principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institutions)
    }

    @Test
    fun `test get all institutions by principal - query`() {
        val institutions = listOf(InstitutionMapper.buildInstitutionDto(program))

        `when`(programService.getAllByPrincipal("query", principal)).thenReturn(institutions)

        val responseEntity0 = programController.getAllByPrincipal("query", principal)

        assert(responseEntity0.statusCode == HttpStatus.OK)
        assert(responseEntity0.body == institutions)
    }

    @Test
    fun `test get a particular institution`() {
        val institution = InstitutionMapper.buildInstitutionDetailDto(program)

        `when`(programService.getById(uuid)).thenReturn(institution)

        val responseEntity = programController.getProgram(uuid)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institution)
    }

    @Test
    fun `test create a particular institution`() {
        val institutionRes = InstitutionMapper.buildInstitutionDto(program)
        val institutionReq = ProgramRequestDto(
            name = program.name,
            description = program.description,
            category = program.category,
            file = fileImg
        )

        `when`(programService.create(institutionReq, principal)).thenReturn(institutionRes)

        val responseEntity = programController.createProgram(institutionReq, principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == institutionRes)
    }

    @Test
    fun `test delete a particular institution`() {
        `when`(programService.delete(uuid, principal)).then { }

        val responseEntity = programController.deleteProgram(uuid, principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == mapOf("message" to "Institution eliminado correctamente."))
    }
}