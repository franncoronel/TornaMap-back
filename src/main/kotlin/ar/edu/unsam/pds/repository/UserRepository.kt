package ar.edu.unsam.pds.repository

import ar.edu.unsam.pds.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>

    fun findAllByOrderByNameAsc(): MutableList<User>
}
