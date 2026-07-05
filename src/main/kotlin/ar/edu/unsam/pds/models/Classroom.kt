package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.models.enums.ClassroomType
import jakarta.persistence.*
import java.io.Serializable
import java.util.UUID

@Entity @Table(name = "APP_CLASSROOM")
class Classroom(
    @Column(unique = true)
    val code: String,
    val name: String,
    val type: ClassroomType,
    val capacity: Int,
    val floor: Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id", nullable = false)
    val building: Building
    ): Serializable {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    fun getFullName(): String {
        return "$name (${building.name})"
    }
}