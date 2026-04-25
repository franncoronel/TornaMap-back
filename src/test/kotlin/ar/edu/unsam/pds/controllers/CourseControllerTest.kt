package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.CourseService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile
import java.util.*

@ExtendWith(MockitoExtension::class)
class CourseControllerTest {
    @Mock
    private lateinit var courseServices: CourseService
    private lateinit var courseController: CourseController
    private lateinit var course: Course
    private lateinit var uuid: String

    private lateinit var principal: Principal
    private lateinit var fileImg: MockMultipartFile

    @BeforeEach
    fun setUp() {
        courseController = CourseController()
        courseController.courseService = courseServices

        course = Course(
            name = "title 1",
            description = "description"
        ).apply {
            id = UUID.randomUUID()
        }

        uuid = course.id.toString()

        principal = Principal().apply {
            username = "admin@admin.com"
            password = "123"
            user = User(
                name = "Admin",
                lastName = "Admin",
                email = "admin@admin.com",
                image = "",
                isAdmin = true
            )
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
    fun `test get all courses - not query`() {
//        val courses = listOf(CourseMapper.buildCourseDto(course))

//        `when`(courseServices.getAll("")).thenReturn(courses)
        val responseEntity = courseController.getAll(null)

        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == courses)
    }

    @Test
    fun `test get all courses - query`() {
//        val courses = listOf(CourseMapper.buildCourseDto(course))

//        `when`(courseServices.getAll("query")).thenReturn(courses)
        val responseEntity = courseController.getAll("query")

        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == courses)
    }

//    @Test
//    fun `test get all courses by principal - not query`() {
//        val courses = listOf(CourseMapper.buildCourseDto(course))
//
//        `when`(courseServices.getAllByPrincipal("", principal)).thenReturn(courses)
//        val responseEntity = coursesController.getAllByPrincipal(null, principal)
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == courses)
//    }

//    @Test
//    fun `test get all courses by principal  - query`() {
//        val courses = listOf(CourseMapper.buildCourseDto(course))
//
//        `when`(courseServices.getAllByPrincipal("query", principal)).thenReturn(courses)
//        val responseEntity = coursesController.getAllByPrincipal("query", principal)
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == courses)
//    }

//    @Test
//    fun `test get a particular course`() {
//        val course = CourseMapper.buildCourseDetailDto(course)
//
//        `when`(courseServices.getCourse(uuid)).thenReturn(course)
//
//        val responseEntity = coursesController.getCourse(uuid)
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == course)
//    }

//    @Test
//    fun `test delete multiple courses`() {
//        val uuids = listOf(uuid)
//
//        `when`(courseServices.deleteAllById(uuids, principal)).then { }
//
//        val responseEntity = coursesController.deleteMultipleCourses(uuids, principal)
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//    }
//
//    @Test
//    fun `test delete a particular course`() {
//        `when`(courseServices.deleteCourse(uuid, principal)).then { }
//
//        val responseEntity = coursesController.deleteCourse(uuid, principal)
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//    }

//    @Test
//    fun `test create a course`() {
//        val course = CourseMapper.buildCourseDto(course)
//
//        val courseRequest = CourseRequestDto(
//            title = "title 1",
//            description = "description"
//        )
//
//        `when`(courseServices.createCourse(courseRequest)).thenReturn(course)
//
//        val responseEntity = coursesController.createCourse(courseRequest)
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == course)
//    }

//    @Test
//    fun `test get course stats`() {
//        val courseStats = CourseStatsResponseDto(
//            id = uuid,
//            title = course.name,
//            description = course.description,
//            category = course.category,
//            image = "",
//            totalAssignments = 0,
//            totalSubscriptions = 0,
//            totalIncome = 0.0,
//            mostPopularAssignment = null,
//            mostProfitableAssignment = null,
//            assignments = mutableSetOf()
//        )
//
//        `when`(courseServices.getCourseStats(uuid)).thenReturn(courseStats)
//
//        val responseEntity = coursesController.getCourseStats(uuid)
//
//        assert(responseEntity.statusCode == HttpStatus.OK)
//        assert(responseEntity.body == courseStats)
//    }
}