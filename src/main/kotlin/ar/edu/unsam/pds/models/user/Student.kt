package ar.edu.unsam.pds.models.user

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Program

class Student(name: String,
              lastName: String,
              email: String, ) : User(name, lastName, email) {

    val programs: MutableSet<Program> = mutableSetOf()
    val suscribedCourses: MutableSet<Course> = mutableSetOf()

    fun suscribeToCourse(course: Course) {
            suscribedCourses.add(course)
        }
        fun unsuscribeToCourse(course:Course){
            suscribedCourses.remove(course)
        }

}