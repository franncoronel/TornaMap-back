package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.exceptions.ValidationException
import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@Entity @Table(name = "APP_PROGRAM")
class Program(
    var name: String,

    @Column(length = 1024)
    var description: String,

    ) : Timestamp(), Serializable {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @ManyToMany(mappedBy = "programs", fetch = FetchType.LAZY)
    val courses: MutableSet<Course> = mutableSetOf()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "app_user_program",
        joinColumns = [JoinColumn(name = "program_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val admin = mutableSetOf<User>()

    fun addCourse(newCourses: List<Course>) {
        courses.addAll(newCourses)
    }

    fun removeCourse(course: Course) {
        hasCourse(course)
        courses.remove(course)
    }

    fun hasCourse(course: Course) {
        if (!courses.contains(course)) {
            throw ValidationException("El curso no forma parte de esta carrera")
        }
    }

    fun addAdmin(user: User) {
        admin.add(user)
    }

}