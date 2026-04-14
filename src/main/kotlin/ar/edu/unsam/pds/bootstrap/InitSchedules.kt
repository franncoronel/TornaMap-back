package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Event
import ar.edu.unsam.pds.models.Schedule
import ar.edu.unsam.pds.models.*
import ar.edu.unsam.pds.models.user.User
import ar.edu.unsam.pds.repository.ClassroomRepository
import ar.edu.unsam.pds.repository.EventRepository
import ar.edu.unsam.pds.repository.ScheduleRepository
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalTime

@Component(value = "InitSchedules.beanName")
@DependsOn(value = ["InitEvents.beanName", "InitClassroom.beanName"])
@Profile(value = ["dev", "prod", "test"])
class InitSchedules : BootstrapGeneric("Schedules") {
    @Autowired private lateinit var eventRepository: EventRepository
    @Autowired private lateinit var userRepository: UserRepository
    @Autowired private lateinit var scheduleRepository: ScheduleRepository
    @Autowired private lateinit var classroomRepository: ClassroomRepository

    override fun doAfterPropertiesSet() {
        val cursadaMate1TM = findEvent("Turno Mañana")
        val cursadaMate1TT = findEvent("Turno Tarde")
        val cursadaMate1TN = findEvent("Turno Noche")
        val cursadaAlgo1 = findEvent("Cursada Algoritmos I")
        val cursadaAlgo2 = findEvent("Cursada Algoritmos II")
//        val cursadaAlgo3 = findEvent("Cursada Algoritmos III")    //Solo se dicta en el segundo cuatri
        val cursadaRedes = findEvent("Cursada Telecomunicaciones y Redes")
        val cursadaPHM = findEvent("Cursada PHM")
        val cursadaMetodosNumericos = findEvent("Cursada Métodos Numéricos")
        val cursadaProyecSoft = findEvent("Cursada Proyectos de Software")
        val cursadaParadigmas = findEvent("Cursada Paradigmas de Programación")
        val cursadaMate2 = findEvent("Cursada Matemática II")
        val cursadaMate3 = findEvent("Cursada Matemática III")
        val cursadaEyMN1 = findEvent("Cursada EyM")
        // val cursadaEyMN2 = findEvent("Cursada EyM - lab Jueves")
        val cursadaEyMT = findEvent("Cursada EyM TT")
        val cursadaCaso = findEvent("Cursada CASO")
        val cursadaMate3b = findEvent("Mate III - Comisión B")
        val cursadaLabo1 = findEvent("Cursada Labo1")
        val cursadaLabo2 = findEvent("Cursada Labo2")
        val cursadaSPD = findEvent("Cursada SPD")

        /*TRI*/
        val cursadaRedesInfo1 = findEvent("Cursada Redes de Información I")
        val cursadaRedesInfo2 = findEvent("Cursada Redes de Información II")
        val cursadaRedesInfo3 = findEvent("Cursada Redes de Información III")

        val carlos = userByEmail("cscirica@estudiantes.unsam.edu.ar")
        val dodino = userByEmail("dodain@estudiantes.unsam.edu.ar")
        val mc = userByEmail("mabeledo@unsam.edu.ar")
        val pedroFIriso=userByEmail("pedroiriso@gmail.com")
        val fabioSergioBruschetti=userByEmail("fSBruschetti@estudiantes.unsam.edu.ar")
        val gastonAguilera=userByEmail("gAguilera@estudiantes.unsam.edu.ar")
        val monica=userByEmail("mHencek@estudiantes.unsam.edu.ar")
        val juanJoseLopez=userByEmail("jJLopez@estudiantes.unsam.edu.ar")
//        val event1 = findEvent("Cursada Algoritmos I")
       /*
        val event3 = findEvent("Final Matematica I")*/

//        val scheduleCharla = Schedule(
//            startTime = LocalTime.of(18, 0),
//            endTime = LocalTime.of(20, 0),
//            weekDay = null,
//            date = LocalDate.of(2025, 4, 14),
//            isVirtual = false,
//        ).apply {
//            event = cursadaMate1TM
//            classroom = classroomRepository.findByName("Aula A28").orElseThrow { NotFoundException("No se halló el aula") }
//            assignUserToSchedule(dodino, this)
//        }
//        scheduleRepository.save(scheduleCharla)


//      Mate 1 - Turno Mañana
        val scheduleMate1TM= Schedule(
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(12, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate1TM
            classroom = classroomRepository.findByName("Aula A19 - Tornavías").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleMate1TM)

        //TODO: Revisar aula
        val scheduleMate1TM2= Schedule(
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(12, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate1TM
            classroom = classroomRepository.findByName("Aula A28").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleMate1TM2)

//      Mate 1 - Turno Tarde
        val scheduleMate1TT= Schedule(
            startTime = LocalTime.of(14, 0),
            endTime = LocalTime.of(18, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate1TT
            classroom = classroomRepository.findByName("Aula A9 - Tornavías").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleMate1TT)

        //TODO: Revisar aula
        val scheduleMate1TT2= Schedule(
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(19, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate1TT
            classroom = classroomRepository.findByName("Aula A28").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleMate1TT2)

//      Mate 1 - Turno Noche
        val scheduleMate1TN= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate1TN
            classroom = classroomRepository.findByName("Aula A19 - Tornavías").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleMate1TN)

        val scheduleMate1TN2= Schedule(
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate1TN
            classroom = classroomRepository.findByName("Aula A15 - Tornavías").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleMate1TN2)

        /*.................:Algo1:...............................*/
        val scheduleAlgo1Martes= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaAlgo1
            classroom = classroomRepository.findByName("Laboratorio de Computación 4").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(juanJoseLopez, this)
        }
        scheduleRepository.save(scheduleAlgo1Martes)

        val scheduleAlgo1jueves= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaAlgo1
            classroom = classroomRepository.findByName("Aula A16 - Tornavías").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(juanJoseLopez, this)
        }
        scheduleRepository.save(scheduleAlgo1jueves)

        /*.................:Algo2:...............................*/
        val scheduleAlgo2Martes= Schedule(
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaAlgo2
            classroom = classroomRepository.findByName("Laboratorio de Computación 1").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleAlgo2Martes)

        val scheduleAlgo2Jueves= Schedule(
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaAlgo2
            classroom = classroomRepository.findByName("Aula A1 - ITS").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleAlgo2Jueves)

        /*.................:Redes:...............................*/
        val scheduleRedesMiercoles= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.WEDNESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedes
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesMiercoles)

        val scheduleRedesViernes= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.FRIDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedes
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesViernes)

        /*.................:Metodos numericos:...............................*/
        val scheduleMetodosNumericos= Schedule(
            startTime = LocalTime.of(16, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMetodosNumericos
            classroom = classroomRepository.findByName("Aula A10 - Tornavías").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleMetodosNumericos)

        /*.................:PHM:...............................*/
        val schedulePHMLunes= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaPHM
            classroom = classroomRepository.findByName("Aula A17 - Tornavías").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(schedulePHMLunes)

        val schedulePHMJueves= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaPHM
            classroom = classroomRepository.findByName("Laboratorio de Computación 4").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(schedulePHMJueves)

        /*.................:Proyectos de Software:...............................*/
        val scheduleProyecDeSoftMiercoles= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.WEDNESDAY,
            date = null,
            isVirtual = true,
        ).apply {
            event = cursadaProyecSoft
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleProyecDeSoftMiercoles)

        val scheduleProyecDeSoftViernes= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.FRIDAY,
            date = null,
            isVirtual = true,
        ).apply {
            event = cursadaProyecSoft
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleProyecDeSoftViernes)

        /*.................:Paradigmas de Prog:...............................*/
        val scheduleParadigmasDeProg= Schedule(
            startTime = LocalTime.of(16, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaParadigmas
            classroom = classroomRepository.findByName("Laboratorio de Computacion 2").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(gastonAguilera, this)
        }
        scheduleRepository.save(scheduleParadigmasDeProg)

        /*.................:Mate2:...............................*/
        val scheduleMate2Martes= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate2
            classroom = classroomRepository.findByName("Aula A1 - ITS").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(carlos, this)
        }
        scheduleRepository.save(scheduleMate2Martes)

        val scheduleMate2Jueves= Schedule(
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(19, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate2
            classroom = classroomRepository.findByName("Aula A17 - Aulario Nave 3").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(carlos, this)
        }
        scheduleRepository.save(scheduleMate2Jueves)

        /*.................:Mate3:...............................*/
        val scheduleMate3= Schedule(
            startTime = LocalTime.of(16, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.FRIDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate3
            classroom = classroomRepository.findByName("Laboratorio de Computación 1").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(monica, this)
        }
        scheduleRepository.save(scheduleMate3)

        val scheduleMate3B= Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.FRIDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaMate3b
            classroom = classroomRepository.findByName("Laboratorio de Computacion 2").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleMate3B)

        /*.................:Caso:...............................*/
        // TODO: Revisar AULA
        val scheduleCasoLunes= Schedule(
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaCaso
            classroom = classroomRepository.findByName("Laboratorio de Computacion 2").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(gastonAguilera, this)
        }
        scheduleRepository.save(scheduleCasoLunes)

        val scheduleCasoMiercoles= Schedule(
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.WEDNESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaCaso
            classroom = classroomRepository.findByName("Taller 05").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(gastonAguilera, this)
        }
        scheduleRepository.save(scheduleCasoMiercoles)

        /*.................:EyM Noche:...............................*/
        val scheduleEyMMartes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaEyMN1
            classroom = classroomRepository.findByName("Aula A15 - Aulario Nave 3").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleEyMMartes)

        val scheduleEyMViernes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.FRIDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaEyMN1
            classroom = classroomRepository.findByName("Laboratorio de Termodinámica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleEyMViernes)
//      TODO: Revisar porque está duplicado
//        val scheduleEyMMartes2 = Schedule(
//            startTime = LocalTime.of(18, 0),
//            endTime = LocalTime.of(22, 0),
//            weekDay = DayOfWeek.TUESDAY,
//            date = null,
//            isVirtual = false,
//        ).apply {
//            event = cursadaEyMN2
//            classroom = classroomRepository.findByName("Aula A15 - Aulario Nave 3").orElseThrow { NotFoundException("No se halló el aula") }
//            assignUserToSchedule(dodino, this)
//        }
//        scheduleRepository.save(scheduleEyMMartes2)
//
//        val scheduleEyMJueves = Schedule(
//            startTime = LocalTime.of(18, 0),
//            endTime = LocalTime.of(22, 0),
//            weekDay = DayOfWeek.MONDAY,
//            date = null,
//            isVirtual = false,
//        ).apply {
//            event = cursadaEyMN2
//            classroom = classroomRepository.findByName("Aula A28").orElseThrow { NotFoundException("No se halló el aula") }
//            assignUserToSchedule(dodino, this)
//        }
//        scheduleRepository.save(scheduleEyMJueves)

        /* Turno tarde */
        val scheduleEyMTMartes = Schedule(
            startTime = LocalTime.of(13, 0),
            endTime = LocalTime.of(17, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaEyMT
            classroom = classroomRepository.findByName("Aula A15 - Aulario Nave 3").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleEyMTMartes)

        val scheduleEyMTViernes = Schedule(
            startTime = LocalTime.of(13, 0),
            endTime = LocalTime.of(17, 0),
            weekDay = DayOfWeek.FRIDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaEyMT
            classroom = classroomRepository.findByName("Laboratorio de Termodinámica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleEyMTViernes)

        /*.................:Labo1:...............................*/
        val scheduleLabo1MiercolesA = Schedule(
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.WEDNESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaLabo1
            classroom = classroomRepository.findByName("Laboratorio de Computación 1").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleLabo1MiercolesA)

        val scheduleLabo1MiercolesB = Schedule(
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.WEDNESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaLabo1
            classroom = classroomRepository.findByName("Laboratorio de Computacion 2").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleLabo1MiercolesB)

        val scheduleLabo1Jueves = Schedule(
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaLabo1
            classroom = classroomRepository.findByName("Laboratorio de Computación 1").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this)
        }
        scheduleRepository.save(scheduleLabo1Jueves)

        /*.................:Labo2:...............................*/
        val scheduleLabo2LunesA = Schedule(
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaLabo2
            classroom = classroomRepository.findByName("Laboratorio de Computacion 2").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(monica, this)
        }
        scheduleRepository.save(scheduleLabo2LunesA)

        val scheduleLabo2LunesB = Schedule(
            startTime = LocalTime.of(17, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaLabo2
            classroom = classroomRepository.findByName("Lab LCD").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this) // TODO: No Asignar a mónica
        }
        scheduleRepository.save(scheduleLabo2LunesB)

        val scheduleLabo2JuevesA = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaLabo2
            classroom = classroomRepository.findByName("Lab LCD").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(monica, this)
        }
        scheduleRepository.save(scheduleLabo2JuevesA)
        val scheduleLabo2JuevesB = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.THURSDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaLabo2
            classroom = classroomRepository.findByName("Aula A28").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(dodino, this) // TODO: NO asignar a Mónica
        }
        scheduleRepository.save(scheduleLabo2JuevesB)

        /*.................:SPD:...............................*/
        val scheduleSPDMiercoles = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.WEDNESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaSPD
            classroom = classroomRepository.findByName("Lab LCD").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(fabioSergioBruschetti, this)

            // TODO: si le pongo a los dos prof en el mismo día me duplica la card
//            assignUserToSchedule(pedroFIriso, this)
        }
        scheduleRepository.save(scheduleSPDMiercoles)

        val scheduleSPDViernes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.FRIDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaSPD
            classroom = classroomRepository.findByName("Lab LCD").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(pedroFIriso, this)
//            assignUserToSchedule(fabioSergioBruschetti, this)
        }
        scheduleRepository.save(scheduleSPDViernes)

        /**********************************************TRI***************************************************************************/
        /*Redes de Información I*/
        val scheduleRedesInfo1Lunes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedesInfo1
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesInfo1Lunes)

        val scheduleRedesInfo1Martes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedesInfo1
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesInfo1Martes)

        /*Redes de Información II*/
        val scheduleRedesInfo2Lunes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedesInfo2
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesInfo2Lunes)

        val scheduleRedesInfo2Martes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedesInfo2
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesInfo2Martes)

        /*Redes de Información III*/
        val scheduleRedesInfo3Lunes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.MONDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedesInfo3
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesInfo3Lunes)

        val scheduleRedesInfo3Martes = Schedule(
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(22, 0),
            weekDay = DayOfWeek.TUESDAY,
            date = null,
            isVirtual = false,
        ).apply {
            event = cursadaRedesInfo3
            classroom = classroomRepository.findByName("Centro de investigacion y desarrollo de informatica").orElseThrow { NotFoundException("No se halló el aula") }
            assignUserToSchedule(mc, this)
        }
        scheduleRepository.save(scheduleRedesInfo3Martes)

    }

    fun findEvent(name: String): Event {
        return eventRepository.findByName(name).orElseThrow{ NotFoundException("No se halló un evento con ese nombre") }
    }


    fun userByEmail(mail: String): User {
        return userRepository.findByEmail(mail).orElseThrow { NotFoundException("No se encontró profesor con el email suministrado") }
    }

}