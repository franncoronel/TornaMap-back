package ar.edu.unsam.pds.models.user

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Event
import ar.edu.unsam.pds.models.Program

class Student(name: String,
              lastName: String,
              email: String, ) : User(name, lastName, email) {

    val programs: MutableSet<Program> = mutableSetOf()

    fun suscribeToEvent(event: Event){
        event.addSuscriber(this.email)
    }

        fun unsusccribeToEvent(event: Event){
            event.suscribers.remove(this.email)
        }
}