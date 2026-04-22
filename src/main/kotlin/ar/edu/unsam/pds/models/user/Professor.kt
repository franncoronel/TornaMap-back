package ar.edu.unsam.pds.models.user

import ar.edu.unsam.pds.models.Classroom
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Event
import ar.edu.unsam.pds.models.Schedule

class Professor(name: String,
                lastName: String,
                email: String, ) : User(name, lastName, email) {

    val courses: MutableSet<Course> = mutableSetOf()

    fun bookClassroom(schedule: Schedule, classroom: Classroom, event: Event, course: Course){
        event.addSchedule(schedule)
        schedule.event = event
        schedule.classroom = classroom
        schedule.assignedUsers.add(this)
        if (validateCourse(course)) {
            event.attachCourse(course)
        }
    }

    private fun validateCourse(course: Course) = courses.contains(course)

}