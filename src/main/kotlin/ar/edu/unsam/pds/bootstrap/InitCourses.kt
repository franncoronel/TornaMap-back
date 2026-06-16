package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles
import org.springframework.stereotype.Component

@Component(value = "InitCourses.beanName")
@Profile(value = ["dev", "prod", "test"])
class InitCourses : BootstrapGeneric("Courses") {
    @Autowired private lateinit var courseRepository: CourseRepository
    @Autowired private lateinit var environment: Environment

    // fun urlBase() = "http://${this.getDomain()}:8080/media/public"

    fun getDomain() =
        if (environment.acceptsProfiles(Profiles.of("prod"))) "149.50.143.203"
        else "localhost"

    override fun doAfterPropertiesSet() {

        courseRepository.save(
            Course(
                name = "Matemática I",
                description = "Asignatura introductoria que aborda los fundamentos del análisis en una variable, álgebra y geometría. Se aprenden conceptos clave como funciones, límites, derivadas, polinomios y ecuaciones, desarrollando habilidades esenciales para el razonamiento matemático y la resolución de problemas.",
                students = 80
            )
        )

        courseRepository.save(
            Course(
                name = "Matemática II",
                description = "Asignatura que profundiza en geometría analítica, lógica proposicional, teoría de conjuntos, números enteros y polinomios, brindando herramientas matemáticas esenciales para la informática.",
                students = 80
            )
        )

        courseRepository.save(
            Course(
                name = "Matemática III",
                description = "Asignatura introductoria que aborda los fundamentos del análisis en una variable, álgebra y geometría. Se aprenden conceptos clave como funciones, límites, derivadas, polinomios y ecuaciones, desarrollando habilidades esenciales para el razonamiento matemático y la resolución de problemas.",
                students = 75
            )
        )

        courseRepository.save(
            Course(
                name = "Electricidad y Magnetismo",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 80
            )
        )

        courseRepository.save(
            Course(
                name = "Laboratorio de Computación I",
                description = "Asignatura introductoria que aborda las nociones básicas de una computadora, utilización de programas e introducción a la resolución de pequeños problemas.",
                students = 80
            )
        )

        courseRepository.save(
            Course(
                name = "Laboratorio de Computación II",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 90
            )
        )

        courseRepository.save(
            Course(
                name = "Telecomunicaciones y Redes",
                description = "Asignatura introductoria que aborda los principios fundamentales de la electricidad y el magnetismo, incluyendo electrostática, circuitos eléctricos, inducción electromagnética y ondas electromagnéticas, con aplicaciones en equipos informáticos y de comunicaciones.",
                students = 75
            )
        )

        courseRepository.save(
            Course(
                name = "Métodos Numéricos",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 70
            )
        )

        courseRepository.save(
            Course(
                name = "Sistemas de Procesamiento de Datos",
                description = "Asignatura que aborda la organización interna de sistemas de computadoras digitales, incluyendo el modelo de Von Neumann, la función del procesador, entrada/salida, software de base y arquitecturas avanzadas.",
                students = 80
            )
        )

        courseRepository.save(
            Course(
                name = "Algoritmos I",
                description = "Asignatura centrada en la especificación, implementación y corrección de programas, abordando el tratamiento de secuencias, tipos abstractos de datos y archivos secuenciales mediante pequeños proyectos computacionales.",
                students = 95
            )
        )

        courseRepository.save(
            Course(
                name = "Algoritmos II",
                description = "Asignatura centrada en la especificación, implementación y corrección de programas, abordando el tratamiento de secuencias, tipos abstractos de datos y archivos secuenciales mediante pequeños proyectos computacionales.",
                students = 90
            )
        )

        courseRepository.save(
            Course(
                name = "Algoritmos III",
                description = "Asignatura centrada en la especificación, implementación y corrección de programas, abordando el tratamiento de secuencias, tipos abstractos de datos y archivos secuenciales mediante pequeños proyectos computacionales.",
                students = 60
            )
        )

        courseRepository.save(
            Course(
                name = "Conceptos de Arquitecturas y Sistemas Operativos",
                description = "Asignatura que introduce las funciones principales de los sistemas operativos y su relación con la arquitectura del computador, incluyendo administración de recursos, procesos concurrentes y sistemas distribuidos.",
                students = 75
            )
        )

        courseRepository.save(
            Course(
                name = "Programación con Herramientas Modernas",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 65
            )
        )

        courseRepository.save(
            Course(
                name = "Proyectos de Software",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 55
            )
        )

        courseRepository.save(
            Course(
                name = "Paradigmas de Programación",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 60
            )
        )

        courseRepository.save(
            Course(
                name = "Base de Datos",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 70
            )
        )

        courseRepository.save(
            Course(
                name = "Seminario de  Programación Concurrente, Paralela y Distribuida",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 40
            )
        )

        courseRepository.save(
            Course(
                name = "Redes de Información I",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 85
            )
        )

        courseRepository.save(
            Course(
                name = "Redes de Información II",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 65
            )
        )

        courseRepository.save(
            Course(
                name = "Redes de Información III",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 45
            )
        )

        courseRepository.save(
            Course(
                name = "Proyecto I",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 50
            )
        )

        courseRepository.save(
            Course(
                name = "Proyecto II",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 40
            )
        )

        courseRepository.save(
            Course(
                name = "Proyecto III",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 30
            )
        )

        courseRepository.save(
            Course(
                name = "Administración de Redes de Computadoras",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 45
            )
        )

        courseRepository.save(
            Course(
                name = "Sistemas Avanzados de Comunicación",
                description = "Asignatura orientada a la resolución de problemas mediante herramientas informáticas, incluyendo programación, redes, sistemas operativos y aplicaciones matemáticas para algoritmos numéricos y problemas algebraicos.",
                students = 35
            )
        )

    }
}
