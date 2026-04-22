package ar.edu.unsam.pds.models.user

import ar.edu.unsam.pds.models.Event

class Admin(name: String, lastName: String, email: String) : User(name, lastName, email)  {
    override var isAdmin: Boolean = true

    fun approveEvent(event: Event){
        event.isApproved = true
    }

    fun denyEvent(event: Event){
        event.isApproved = false
    }

    //funcion en repositorio que limpie eventos?
}