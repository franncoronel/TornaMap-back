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
            code = "A28",
            name = "Aula A28",
            capacity = 30,
            floor = -1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )

        val salaLectura = Classroom(
            code = "SAL",
            name = "Sala de Lectura",
            capacity = 20,
            floor = 1,
            type = ClassroomType.LECTURE,
            building = tornavias
        )

        val carpaAuditorio = Classroom(
            code = "CAR",
            name = "Carpa Auditorio",
            capacity = 100,
            floor = 0,
            type = ClassroomType.AUDITORIUM,
            building = tornavias
        )

        //PLANTA BAJA
        val cidi = Classroom(
            code = "CIDI",
            name = "Centro de investigacion y desarrollo de informatica",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )

        val aulaA10 = Classroom(
            code = "A10",
            name = "Aula A10 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )

        val aulaA6 = Classroom(
            code = "A6",
            name = "Aula A6",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )

        val aulaA6Bis = Classroom(
            code = "A6BIS",
            name = "Aula A6 BIS",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA6Bis)

        val aulaA7 = Classroom(
            code = "A7",
            name = "Aula A7",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )

        val aulaA5 = Classroom(
            code = "A5",
            name = "Aula A5",
            capacity = 50,
            floor = 0,
            type=  ClassroomType.CLASSROOM,
            building = tornavias
        )

        val aulaA1 = Classroom(
            code = "A1",
            name = "Aula A1",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA1)

        val aulaA2 = Classroom(
            code = "A2",
            name = "Aula A2",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA2)

        val aulaA3 = Classroom(
            code = "A3",
            name = "Aula A3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA3)

        val aulaA4 = Classroom(
            code = "A4",
            name = "Aula A4",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA4)

        val aulaA8 = Classroom(
            code = "A8",
            name = "Aula A8",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA8)

        val aulaA9 = Classroom(
            code = "A9",
            name = "Aula A9 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA9)

        val aulaA11 = Classroom(
            code = "A11",
            name = "Aula A11",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA11)

        val aulaA12 = Classroom(
            code = "A12",
            name = "Aula A12",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA12)

        val aulaA13 = Classroom(
            code = "A13",
            name = "Aula A13",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA13)

        val aulaA14 = Classroom(
            code = "A14",
            name = "Aula A14",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA14)

        val aulaA15 = Classroom(
            code = "A15",
            name = "Aula A15 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA15)

        val aulaA16 = Classroom(
            code = "A16",
            name = "Aula A16 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA16)

        val aulaA17 = Classroom(
            code = "A17",
            name = "Aula A17 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA17)

        val aulaA18 = Classroom(
            code = "A18",
            name = "Aula A18",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA18)

        val aulaA19 = Classroom(
            code = "A19",
            name = "Aula A19 - Tornavías",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA19)

        val aulaA20 = Classroom(
            code = "A20",
            name = "Aula A20",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA20)

        val aulaA21 = Classroom(
            code = "A21",
            name = "Aula A21",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA21)

        val aulaA22 = Classroom(
            code = "A22",
            name = "Aula A22",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA22)

        val aulaA23 = Classroom(
            code = "A23",
            name = "Aula A23",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA23)

        val aulaA24 = Classroom(
            code = "A24",
            name = "Aula A24",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA24)

        val laboQuimica = Classroom(
            code = "LQ",
            name = "Laboratorio de Quimica",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboQuimica)

        val laboBiologia= Classroom(
            code = "LB",
            name = "Laboratorio de Biologia",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboBiologia)

        val laboFisica = Classroom(
            code = "LF",
            name = "Laboratorio de Física",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboFisica)

        val laboNeuro = Classroom(
            code = "LN",
            name = "Laboratorio de Neuroingeniería",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboNeuro)

        val laboElectronica2 = Classroom(
            code = "LE2",
            name = "Laboratorio de Electrónica 2",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica2)

        val laboTermodinamica = Classroom(
            code = "LT",
            name = "Laboratorio de Termodinámica",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboTermodinamica)

        val laboComputacion4 = Classroom(
            code = "LC4",
            name = "Laboratorio de Computación 4",
            capacity = 50,
            floor = 0,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion4)

        val t01 = Classroom(
            code = "T01",
            name = "Taller 01",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t01)

        val t02 = Classroom(
            code = "T02",
            name = "Taller 02",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t02)

        
        //1° PISO
        val cidi = Classroom(
            code = "CIDI",
            name = "Centro de investigacion y desarrollo de informatica",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )

        val  aulaA30= Classroom(
            code = "A30",
            name = "Aula A30",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA30)

        val  aulaA31= Classroom(
            code = "A31",
            name = "Aula A31",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA31)

        val  aulaA32= Classroom(
            code = "A32",
            name = "Aula 32",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA32)

        val  aulaA33= Classroom(
            code = "A33",
            name = "Aula A33",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA33)

//        val  aulaA34= Classroom(
//            code = "A34",
//            name = "Aula A34",
//            capacity = 50,
//            floor = 1,
//            type = ClassroomType.CLASSROOM,
//            building = tornavias
//        )
//        classroomRepository.save(aulaA34)

        val aulaA35= Classroom(
            code = "A35",
            name = "Aula A35",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(aulaA35)

        val laboComputacion1 = Classroom(
            code = "LC1",
            name = "Laboratorio de Computación 1",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion1)

        val laboComputacion2= Classroom(
            code = "LC2",
            name = "Laboratorio de Computacion 2",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion2)

        val laboComputacion3 = Classroom(
            code = "LC3",
            name = "Laboratorio de Computación 3",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboComputacion3)

        val laboImagenes = Classroom(
            code = "LI",
            name = "Laboratorio de Imágenes",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboImagenes)

        val laboOptica = Classroom(
            code = "LO",
            name = "Laboratorio de Óptica",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboOptica)

        val laboBiomedica = Classroom(
            code = "LBM",
            name = "Laboratorio de Biomédica",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboBiomedica)

        val laboElectronica1 = Classroom(
            code = "LE1",
            name = "Laboratorio de Electrónica 1",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica1)

        val laboElectronica3 = Classroom(
            code = "LE3",
            name = "Laboratorio de Electrónica 3",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica3)

        val laboElectronica4 = Classroom(
            code = "LE4",
            name = "Laboratorio de Electrónica 4",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboElectronica4)

        val laboCienciasHumanas = Classroom(
            code = "LCH",
            name = "Laboratorio de Ciencias Humanas",
            capacity = 50,
            floor = 1,
            type = ClassroomType.LABORATORY,
            building = tornavias
        )
        classroomRepository.save(laboCienciasHumanas)

        val t03 = Classroom(
            code = "T03",
            name = "Taller 03",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t03)

        val t04 = Classroom(
            code = "T04",
            name = "Taller 04",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t04)

        val t05 = Classroom(
            code = "T05",
            name = "Taller 05",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = tornavias
        )
        classroomRepository.save(t05)

        val t06 = Classroom(
            code = "T06",
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

        // Aulario Nave A
//        val aularioNA= findBuildingByName("Aulario Nave A")
//
//        val aulaA4NaveA=Classroom(
//            code = "A4",
//            name = "Aula A4",
//            capacity = 50,
//            floor = 1,
//            type = ClassroomType.CLASSROOM,
//            building = aularioNA
//        )
//        classroomRepository.save(aulaA4NaveA)

        //        AULARIO NAVE 1
//        val aularioN1= findBuildingByName("Aulario Nave 1")
//
//        val aulaA10Nave1 = Classroom(
//            code = "A10",
//            name = "Aula A10 - Aulario Nave 1",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN1
//        )
//        classroomRepository.save(aulaA10Nave1)
//
//        val aulaA9Nave1 = Classroom(
//            code = "AU1-A9",
//            name = "Aula A9 - Aulario Nave 1",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN1
//        )
//        classroomRepository.save(aulaA9Nave1)
//
//        val aulaA8Nave1 = Classroom(
//            code = "A8",
//            name = "Aula A8",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN1
//        )
//        classroomRepository.save(aulaA8Nave1)
//
//        val aulaA7Nave1 = Classroom(
//            code = "A7",
//            name = "Aula A7",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN1
//        )
//        classroomRepository.save(aulaA7Nave1)

//       AULARIO NAVE 2
//        val aularioN2= findBuildingByName("Aulario Nave 2")
//
//        val aulaA13Nave2= Classroom(
//            code = "A13",
//            name = "Aula A13",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN2
//        )
//        classroomRepository.save(aulaA13Nave2)
//
//        val aulaA14Nave2= Classroom(
//            code = "A14",
//            name = "Aula A14",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN2
//        )
//        classroomRepository.save(aulaA14Nave2)
//
//        val aulaA12Nave2= Classroom(
//            code = "A12",
//            name = "Aula A12",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN2
//        )
//        classroomRepository.save(aulaA12Nave2)
//
//        val aulaA11Nave2= Classroom(
//            code = "A11",
//            name = "Aula A11",
//            capacity = 50,
//            floor = 0,
//            type = ClassroomType.CLASSROOM,
//            building = aularioN2
//        )
//        classroomRepository.save(aulaA11Nave2)

        /*...........:Aulario Nave 3:.................*/
        val aularioN3= findBuildingByName("Aulario Nave 3")
        //PLANTA BAJA
        val aulaA17Nave3= Classroom(
            code = "A17",
            name = "Aula A17 - Aulario Nave 3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA17Nave3)

        val aulaA16Nave3= Classroom(
            code = "A16",
            name = "Aula A16 - Aulario Nave 3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA16Nave3)

        val aulaA15Nave3= Classroom(
            code = "A15",
            name = "Aula A15 - Aulario Nave 3",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA15Nave3)

        //1°PISO
        val aulaA19Nave3= Classroom(
            code = "A19",
            name = "Aula A19 - Aulario Nave 3",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA19Nave3)


        val aulaA18Nave3= Classroom(
            code = "A18",
            name = "Aula A18",
            capacity = 50,
            floor = 1,
            type = ClassroomType.CLASSROOM,
            building = aularioN3
        )
        classroomRepository.save(aulaA18Nave3)


        val aulaA20Nave3= Classroom(
            code = "A20",
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
            code = "A1",
            name = "Aula A1 - ITS",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = its
        )
        classroomRepository.save(aulaA1ITS)
        val aulaLabCLDITS= Classroom(
            code = "LabLCD",
            name = "Lab LCD",
            capacity = 50,
            floor = 0,
            type = ClassroomType.CLASSROOM,
            building = its
        )
        classroomRepository.save(aulaLabCLDITS)


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