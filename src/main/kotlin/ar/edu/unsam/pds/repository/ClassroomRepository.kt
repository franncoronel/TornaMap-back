package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.Classroom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface ClassroomRepository : JpaRepository<Classroom, UUID>{

    fun findByName(@Param("name") name: String): Optional<Classroom>

    fun findClassroomByCode(code: String): Optional<Classroom>
}