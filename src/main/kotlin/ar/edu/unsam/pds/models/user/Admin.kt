package ar.edu.unsam.pds.models.user

import ar.edu.unsam.pds.models.Event

class Admin(name: String, lastName: String, email: String) : User(name, lastName, email)  {
    override var isAdmin: Boolean = true
    val pendingEvents: MutableSet<Event> = mutableSetOf()

    fun approveEvent(event: Event){
        event.isApproved = true
        pendingEvents.remove(event)
    }

    fun denyEvent(event: Event){
        event.isApproved = false
        pendingEvents.remove(event)
    }

    //funcion en repositorio que limpie eventos?
}