package ar.edu.unsam.pds.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "APP_USER")
class User(
    var name: String,
    var lastName: String,
    @Column(unique = true) var email: String,
    var image: String = "",
    var isAdmin: Boolean = false,
    val role: Role

) : Timestamp(), Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "app_student_program",
        joinColumns = [JoinColumn(name = "student_id")],
        inverseJoinColumns = [JoinColumn(name = "program_id")]
    )
    val programs: MutableSet<Program> = mutableSetOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "app_professor_course",
        joinColumns = [JoinColumn(name = "professor_id")],
        inverseJoinColumns = [JoinColumn(name = "course_id")]
    )
    val courses: MutableSet<Course> = mutableSetOf()


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "app_user_schedule",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "schedule_id")]
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    val scheduleList = mutableSetOf<Schedule>()

    fun fullName(): String {
        return "$name $lastName"
    }

}

enum class Role {
    ADMIN, STUDENT, PROFESSOR
}