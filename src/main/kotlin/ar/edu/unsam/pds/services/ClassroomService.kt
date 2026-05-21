package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Classroom
import ar.edu.unsam.pds.repository.ClassroomRepository
import org.springframework.stereotype.Service

@Service
class ClassroomService(
    private val classroomRepository: ClassroomRepository,
) {
    fun getById(classroomId: String): Classroom {
        return classroomRepository.findClassroomByCode(classroomId).orElseThrow {
            NotFoundException("Aula no encontrada para el código suministrado")
        }
    }

    fun getAll(): List<Classroom> {
        return classroomRepository.findAll()
    }

}