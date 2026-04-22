package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.CustomResponse
import ar.edu.unsam.pds.mappers.ClassroomMapper
import ar.edu.unsam.pds.services.ClassroomService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/classroom")
@CrossOrigin("*")
class ClassroomController : UUIDValid() {
    @Autowired
    private lateinit var classroomService: ClassroomService

    @GetMapping("/{classroomID}")
    @Operation(summary = "Get classroom by id")
    fun getById(@PathVariable classroomID: String,): ResponseEntity<CustomResponse> {
        return ResponseEntity.status(200).body(
            CustomResponse (
                message = "Aula obtenida con exito",
                data = ClassroomMapper.buildClassroomDto(classroomService.getById(classroomID))
            )
        )
    }

}