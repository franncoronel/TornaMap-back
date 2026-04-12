package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Event
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.EventRepository
import ar.edu.unsam.pds.repository.ProgramRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Period
import ar.edu.unsam.pds.repository.PeriodRepository
import org.springframework.context.annotation.Profile
import java.time.LocalDate
import ar.edu.unsam.pds.models.enums.EventType

@Component(value = "InitEvents.beanName")
@DependsOn(value = ["InitCourses.beanName", "InitPrograms.beanName"])
@Profile(value = ["dev", "prod", "test"])
class InitEvents : BootstrapGeneric("Events") {
    @Autowired
    private lateinit var periodRepository: PeriodRepository

    @Autowired private lateinit var programRepository: ProgramRepository
    @Autowired private lateinit var eventRepository: EventRepository

    override fun doAfterPropertiesSet() {
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val mate1 = this.findCourseByName(name = "Matemática I")
        val algo1 = this.findCourseByName(name = "Algoritmos I")
        val algo2 = this.findCourseByName(name = "Algoritmos II")
        val algo3 = this.findCourseByName(name = "Algoritmos III")
        val telecomuncacionesYRedes = this.findCourseByName(name = "Telecomunicaciones y Redes")
        val progHerrModer = this.findCourseByName(name = "Programación con Herramientas Modernas")
        val proyectosDeSoft = this.findCourseByName(name = "Proyectos de Software")
        val paradigmasDeProg = this.findCourseByName(name = "Paradigmas de Programación")
        val electricidadYMagnetismo = this.findCourseByName(name = "Electricidad y Magnetismo")
        val mate2 = this.findCourseByName(name = "Matemática II")
        val mate3 = this.findCourseByName(name = "Matemática III")
        val metodosNumericos = this.findCourseByName(name = "Métodos Numéricos")
        val caso = this.findCourseByName(name = "Conceptos de Arquitecturas y Sistemas Operativos")
        val laboratorioCompu1 = this.findCourseByName(name = "Laboratorio de Computación I")
        val laboratorioCompu2 = this.findCourseByName(name = "Laboratorio de Computación II")
        val spd = this.findCourseByName(name = "Sistemas de Procesamiento de Datos")
        val bdd = this.findCourseByName(name = "Base de Datos")
        val seminario = this.findCourseByName(name = "Seminario de  Programación Concurrente, Paralela y Distribuida")
        /*TRI*/
        val redesInfo1=this.findCourseByName(name = "Redes de Información I")
        val redesInfo2=this.findCourseByName(name = "Redes de Información II")
        val redesInfo3=this.findCourseByName(name = "Redes de Información III")
        val proyecto1=this.findCourseByName(name = "Proyecto I")
        val proyecto2=this.findCourseByName(name = "Proyecto II")
        val proyecto3=this.findCourseByName(name = "Proyecto III")
        val adminRedesCompu=this.findCourseByName(name = "Administración de Redes de Computadoras")
        val sistAvanzadisComunicacion=this.findCourseByName(name = "Sistemas Avanzados de Comunicación")

        val primerCuatrimestre = Period("Primer Cuatrimestre 2025", LocalDate.parse("2025-03-01"),LocalDate.parse("2025-07-31"))
        periodRepository.save(primerCuatrimestre)
        val segundoCuatrimestre = Period("Segundo Cuatrimestre 2025", LocalDate.parse("2025-08-01"),LocalDate.parse("2025-11-30"))
        periodRepository.save(segundoCuatrimestre)

        val cursadaMate1TM = Event(
            name = "Turno Mañana",
            isApproved = true,
            course = mate1!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaMate1TM)

        val cursadaMate1TT = Event(
            name = "Turno Tarde",
            isApproved = true,
            course = mate1,
            type = EventType.CURSADA,
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaMate1TT)

        val cursadaMate1TN = Event(
            name = "Turno Noche",
            isApproved = true,
            course = mate1
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaMate1TN)

        val cursadaMate2 = Event(
            name = "Cursada Matemática II",
            isApproved = true,
            course = mate2!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaMate2)

        val cursadaMate3 = Event(
            name = "Cursada Matemática III",
            isApproved = true,
            course = mate3!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaMate3)

        val cursadaMate3B = Event(
            name = "Mate III - Comisión B",
            isApproved = true,
            course = mate3,
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaMate3B)

        val cursadaAlgo1 = Event(
            name = "Cursada Algoritmos I",
            isApproved = true,
            course = algo1!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaAlgo1)

        val cursadaAlgo2 = Event(
            name = "Cursada Algoritmos II",
            isApproved = true,
            course = algo2!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaAlgo2)

        val redes = Event(
            name = "Cursada Telecomunicaciones y Redes",
            isApproved = true,
            course = telecomuncacionesYRedes!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(redes)

        val metodosNum = Event(
            name = "Cursada Métodos Numéricos",
            isApproved = true,
            course = metodosNumericos!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(metodosNum)

        val phm = Event(
            name = "Cursada PHM",
            isApproved = true,
            course = progHerrModer!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(phm)

        val proyecDeSoft = Event(
            name = "Cursada Proyectos de Software",
            isApproved = true,
            course = proyectosDeSoft!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(proyecDeSoft)

        val paradigmas = Event(
            name = "Cursada Paradigmas de Programación",
            isApproved = true,
            course = paradigmasDeProg!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(paradigmas)

        val cursadaEyMN1= Event(
            name = "Cursada EyM",
            isApproved = true,
            course = electricidadYMagnetismo!!,
            type = EventType.CURSADA
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaEyMN1)

        val cursadaEyMN2= Event(
            name = "Cursada EyM - lab Jueves",
            isApproved = true,
            course = electricidadYMagnetismo
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaEyMN2)

        val cursadaEyMT= Event(
            name = "Cursada EyM TT",
            isApproved = true,
            course = electricidadYMagnetismo,
            type = EventType.CURSADA
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaEyMT)

        val cursadaCASO= Event(
            name = "Cursada CASO",
            isApproved = true,
            course = caso!!,
            type = EventType.CURSADA
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaCASO)

        val cursadaLabo1 = Event(
            name = "Cursada Labo1",
            isApproved = true,
            course = laboratorioCompu1!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaLabo1)

        val cursadaLabo2 = Event(
            name = "Cursada Labo2",
            isApproved = true,
            course = laboratorioCompu2!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaLabo2)

        val cursadaSPD = Event(
            name = "Cursada SPD",
            isApproved = true,
            course = spd!!,
            type = EventType.CURSADA
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaSPD)

         /*TPI 2do Cuatri*/
        //TODO:NO BORRAR LO COMENTADO
        /*val cursadaAlgo3 = Event(
             name = "Cursada Algoritmos III",
             isApproved = true,
             course = algo3!!
         ).apply {
             this.addPeriod(segundoCuatrimestre)
         }
         eventRepository.save(cursadaAlgo3)

        val cursadaBDD = Event(
            name = "Cursada Base de Datos",
            isApproved = true,
            course = bdd!!
        ).apply {
            this.addPeriod(segundoCuatrimestre)
        }
        eventRepository.save(cursadaBDD)

        val cursadaSeminario = Event(
            name = "Cursada Seminario de  Programación Concurrente, Paralela y Distribuida",
            isApproved = true,
            course = seminario!!
        ).apply {
            this.addPeriod(segundoCuatrimestre)
        }
        eventRepository.save(cursadaSeminario)*/

        /*Materias de TRI*/
        val cursadaRedesInfo1 = Event(
            name = "Cursada Redes de Información I",
            isApproved = true,
            course = redesInfo1!!,
            type = EventType.CURSADA
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaRedesInfo1)

        val cursadaRedesInfo2 = Event(
            name = "Cursada Redes de Información II",
            isApproved = true,
            course = redesInfo2!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaRedesInfo2)

        val cursadaRedesInfo3 = Event(
            name = "Cursada Redes de Información III",
            isApproved = true,
            course = redesInfo3!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaRedesInfo3)
// TODO: NO BORRAR LO COMENTADO
       /* val cursadaProyecto1 = Event(
            name = "Cursada Proyecto I",
            isApproved = true,
            course = proyecto1!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaProyecto1)

        val cursadaProyecto2 = Event(
            name = "Cursada Proyecto II",
            isApproved = true,
            course = proyecto2!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaProyecto2)

        val cursadaProyecto3 = Event(
            name = "Cursada Proyecto III",
            isApproved = true,
            course = proyecto3!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaProyecto3)

        val cursadaAdminRedesCompu = Event(
            name = "Cursada Administración de Redes de Computadoras",
            isApproved = true,
            course = adminRedesCompu!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaAdminRedesCompu)

        val cursadaSistAvanzadisComunicacion = Event(
            name = "Cursada Sistemas Avanzados de Comunicación",
            isApproved = true,
            course = sistAvanzadisComunicacion!!
        ).apply {
            this.addPeriod(primerCuatrimestre)
        }
        eventRepository.save(cursadaSistAvanzadisComunicacion)*/

      }

    fun findCourseByName(name: String): Course? {
        val programsList = programRepository.findAll()
        val allCourses = programsList.map { it.courses }.flatten()
        if (allCourses.isEmpty()) {
            throw NotFoundException("No se hallaron materias")
        }
        return allCourses.find { it.name == name }
    }
}