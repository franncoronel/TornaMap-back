package ar.edu.unsam.pds.models.user

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Program

class Student(name: String,
              lastName: String,
              email: String, ) : User(name, lastName, email) {

    var programs: MutableSet<Program> = mutableSetOf()

}