package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Building
import ar.edu.unsam.pds.models.Classroom
import ar.edu.unsam.pds.models.enums.ClassroomType
import ar.edu.unsam.pds.repository.BuildingRepository
import ar.edu.unsam.pds.repository.ClassroomRepository
import org.springframework.stereotype.Component
import org.springframework.context.annotation.DependsOn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile

@Component(value = "InitClassroom.beanName")
@DependsOn(value = ["InitBuilding.beanName"])
@Profile(value = ["dev", "prod", "test"])
class InitClassroom : BootstrapGeneric("Classroom") {
    @Autowired private lateinit var classroomRepository: ClassroomRepository
    @Autowired private lateinit var buildingRepository: BuildingRepository

    override fun doAfterPropertiesSet() {
        val tornavias = findBuildingByName("Tornavías")
        val its = findBuildingByName("ITS")


        val aulaA28 = Classroom(
            code = "TOR-A28",
            name = "Aula A28",
            capacity = 30,
            floor = -1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )

        val salaLectura = Classroom(
            code = "TOR-SAL",
            name = "Sala de Lectura",
            capacity = 20,
            floor = 1,
            type = ClassroomType.LECTURE,
            building = tornavias
        )

        val carpaAuditorio = Classroom(
            code = "TOR-CAR",
            name = "Carpa Auditorio",
            capacity = 100,
            floor = 0,
            type = ClassroomType.AUDITORIUM,
            building = tornavias
        )

        //PLANTA BAJA
        val aulaA10 = Classroom(
            code = "TOR-A10",
            name = "Aula A10 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )

        val aulaA6 = Classroom(
            code = "TOR-A6",
            name = "Aula A6",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )

        val aulaA6Bis = Classroom(
            code = "TOR-A6BIS",
            name = "Aula A6 BIS",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA6Bis)

        val aulaA7 = Classroom(
            code = "TOR-A7",
            name = "Aula A7",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )

        val aulaA5 = Classroom(
            code = "TOR-A5",
            name = "Aula A5",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )

        val aulaA1 = Classroom(
            code = "TOR-A1",
            name = "Aula A1",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA1)

        val aulaA2 = Classroom(
            code = "TOR-A2",
            name = "Aula A2",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA2)

        val aulaA3 = Classroom(
            code = "TOR-A3",
            name = "Aula A3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA3)

        val aulaA4 = Classroom(
            code = "TOR-A4",
            name = "Aula A4",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA4)

        val aulaA8 = Classroom(
            code = "TOR-A8",
            name = "Aula A8",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA8)

        val aulaA9 = Classroom(
            code = "TOR-A9",
            name = "Aula A9 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA9)

        val aulaA11 = Classroom(
            code = "TOR-A11",
            name = "Aula A11",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA11)

        val aulaA12 = Classroom(
            code = "TOR-A12",
            name = "Aula A12",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA12)

        val aulaA13 = Classroom(
            code = "TOR-A13",
            name = "Aula A13",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA13)

        val aulaA14 = Classroom(
            code = "TOR-A14",
            name = "Aula A14",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA14)

        val aulaA15 = Classroom(
            code = "TOR-A15",
            name = "Aula A15 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA15)

        val aulaA16 = Classroom(
            code = "TOR-A16",
            name = "Aula A16 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA16)

        val aulaA17 = Classroom(
            code = "TOR-A17",
            name = "Aula A17 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA17)

        val aulaA18 = Classroom(
            code = "TOR-A18",
            name = "Aula A18",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA18)

        val aulaA19 = Classroom(
            code = "TOR-A19",
            name = "Aula A19 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA19)

        val aulaA20 = Classroom(
            code = "TOR-A20",
            name = "Aula A20",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA20)

        val aulaA21 = Classroom(
            code = "TOR-A21",
            name = "Aula A21",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA21)

        val aulaA22 = Classroom(
            code = "TOR-A22",
            name = "Aula A22",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA22)

        val aulaA23 = Classroom(
            code = "TOR-A23",
            name = "Aula A23",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA23)

        val aulaA24 = Classroom(
            code = "TOR-A24",
            name = "Aula A24",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA24)

        val laboQuimica = Classroom(
            code = "TOR-LQ",
            name = "Laboratorio de Quimica",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboQuimica)

        val laboBiologia= Classroom(
            code = "TOR-LB",
            name = "Laboratorio de Biologia",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboBiologia)

        val laboFisica = Classroom(
            code = "TOR-LF",
            name = "Laboratorio de Física",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboFisica)

        val laboNeuro = Classroom(
            code = "TOR-LN",
            name = "Laboratorio de Neuroingeniería",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboNeuro)

        val laboElectronica2 = Classroom(
            code = "TOR-LE2",
            name = "Laboratorio de Electrónica 2",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica2)

        val laboTermodinamica = Classroom(
            code = "TOR-LT",
            name = "Laboratorio de Termodinámica",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboTermodinamica)

        val laboComputacion4 = Classroom(
            code = "TOR-LC4",
            name = "Laboratorio de Computación 4",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion4)

        val t01 = Classroom(
            code = "TOR-T01",
            name = "Taller 01",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t01)

        val t02 = Classroom(
            code = "TOR-T02",
            name = "Taller 02",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t02)

        //1° PISO
        val cidi = Classroom(
            code = "TOR-CIDI",
            name = "Centro de investigacion y desarrollo de informatica",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )

        val aulaA30= Classroom(
            code = "TOR-A30",
            name = "Aula A30",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA30)

        val aulaA31= Classroom(
            code = "TOR-A31",
            name = "Aula A31",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA31)

        val aulaA32= Classroom(
            code = "TOR-A32",
            name = "Aula 32",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA32)

        val aulaA33= Classroom(
            code = "TOR-A33",
            name = "Aula A33",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA33)

        val aulaA35= Classroom(
            code = "TOR-A35",
            name = "Aula A35",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA35)

        val laboComputacion1 = Classroom(
            code = "TOR-LC1",
            name = "Laboratorio de Computación 1",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion1)

        val laboComputacion2= Classroom(
            code = "TOR-LC2",
            name = "Laboratorio de Computacion 2",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion2)

        val laboComputacion3 = Classroom(
            code = "TOR-LC3",
            name = "Laboratorio de Computación 3",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion3)

        val laboImagenes = Classroom(
            code = "TOR-LI",
            name = "Laboratorio de Imágenes",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboImagenes)

        val laboOptica = Classroom(
            code = "TOR-LO",
            name = "Laboratorio de Óptica",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboOptica)

        val laboBiomedica = Classroom(
            code = "TOR-LBM",
            name = "Laboratorio de Biomédica",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboBiomedica)

        val laboElectronica1 = Classroom(
            code = "TOR-LE1",
            name = "Laboratorio de Electrónica 1",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica1)

        val laboElectronica3 = Classroom(
            code = "TOR-LE3",
            name = "Laboratorio de Electrónica 3",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica3)

        val laboElectronica4 = Classroom(
            code = "TOR-LE4",
            name = "Laboratorio de Electrónica 4",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica4)

        val laboCienciasHumanas = Classroom(
            code = "TOR-LCH",
            name = "Laboratorio de Ciencias Humanas",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboCienciasHumanas)

        val t03 = Classroom(
            code = "TOR-T03",
            name = "Taller 03",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t03)

        val t04 = Classroom(
            code = "TOR-T04",
            name = "Taller 04",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t04)

        val t05 = Classroom(
            code = "TOR-T05",
            name = "Taller 05",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t05)

        val t06 = Classroom(
            code = "TOR-T06",
            name = "Taller 06",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t06)

        classroomRepository.save(cidi)
        classroomRepository.save(aulaA10)
        classroomRepository.save(aulaA28)
        classroomRepository.save(salaLectura)
        classroomRepository.save(carpaAuditorio)
        classroomRepository.save(aulaA6)
        classroomRepository.save(aulaA7)
        classroomRepository.save(aulaA5)

        /*...........:Aulario Nave 3:.................*/
        val aularioN3= findBuildingByName("Aulario Nave 3")

        //PLANTA BAJA
        val aulaA17Nave3= Classroom(
            code = "AN3-A17",
            name = "Aula A17 - Aulario Nave 3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA17Nave3)

        val aulaA16Nave3= Classroom(
            code = "AN3-A16",
            name = "Aula A16 - Aulario Nave 3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA16Nave3)

        val aulaA15Nave3= Classroom(
            code = "AN3-A15",
            name = "Aula A15 - Aulario Nave 3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA15Nave3)

        //1°PISO
        val aulaA19Nave3= Classroom(
            code = "AN3-A19",
            name = "Aula A19 - Aulario Nave 3",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA19Nave3)

        val aulaA18Nave3= Classroom(
            code = "AN3-A18",
            name = "Aula A18",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA18Nave3)

        val aulaA20Nave3= Classroom(
            code = "AN3-A20",
            name = "Aula A20",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA20Nave3)

        /*...........:ITS:.................*/
        //PLANTA BAJA
        val aulaA1ITS= Classroom(
            code = "ITS-A1",
            name = "Aula A1 - ITS",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = its
        )
        classroomRepository.save(aulaA1ITS)

        val aulaA2ITS= Classroom(
            code = "ITS-A2",
            name = "Aula A2 - ITS",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = its
        )
        classroomRepository.save(aulaA2ITS)

        val aulaA3ITS= Classroom(
            code = "ITS-A3",
            name = "Aula A3 - ITS",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = its
        )
        classroomRepository.save(aulaA3ITS)

        val aulaA4ITS= Classroom(
            code = "ITS-A4",
            name = "Aula A4 - ITS",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = its
        )
        classroomRepository.save(aulaA4ITS)
    }

    fun findBuildingByName(name: String) : Building {
        val allBuildings = buildingRepository.findAll()
        validateBuildingList(allBuildings)
        validateBuildingSearch(allBuildings,name)
        return allBuildings.find { it.name == name }!!
    }

    fun validateBuildingList(buildings: List<Building>) {
        if (buildings.isEmpty()) {
            throw NotFoundException("No hay edificios cargados")
        }
    }

    fun validateBuildingSearch(buildings: List<Building>, buildingName: String) {
        if (!buildings.map {it.name}.contains(buildingName)) {
            throw NotFoundException("No hay un edificio con el nombre indicado.")
        }
    }
}