package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.BootstrapBasicTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class EventTest : BootstrapBasicTest() {
    @BeforeEach
    fun setUpAssignmentTest() {
        users[0].id = UUID.randomUUID()
        users[1].id = UUID.randomUUID()
        users[2].id = UUID.randomUUID()
    }

    @Test
    fun `get name day of assignment`() {
        assertEquals(events[0].activeDays(), "MONDAY")
    }

    @Test
    fun `get total income`() {
        events[0].addSubscribedUser(users[0])
        events[0].addSubscribedUser(users[1])
        events[0].addSubscribedUser(users[2])

        assertEquals(events[0].totalIncome(), 300.0)
    }

    @Test
    fun `get total subscribed users`() {
        events[0].addSubscribedUser(users[0])
        events[0].addSubscribedUser(users[1])
        events[0].addSubscribedUser(users[2])

        assertEquals(events[0].totalSubscribedUsers(), 3)
    }

    @Test
    fun `Try has any subscribed user - 1`() {
        events[0].addSubscribedUser(users[0])

        assertTrue(events[0].hasAnySubscribedUser())
    }

    @Test
    fun `Try has any subscribed user - 2`() {
        assertFalse(events[0].hasAnySubscribedUser())
    }

    @Test
    fun `Try deleting an user to the assignment`() {
        events[0].addSubscribedUser(users[0])
        events[0].addSubscribedUser(users[1])
        events[0].addSubscribedUser(users[2])

        assertEquals(users.toMutableSet(), events[0].subscribedUsers)

        events[0].removeSubscribedUser(users[0])

        assertEquals(mutableSetOf(users[1], users[2]), events[0].subscribedUsers)
    }

    @Test
    fun `Try add subscribed user`() {
        assertEquals(mutableSetOf<User>(), events[0].subscribedUsers)

        events[0].addSubscribedUser(users[0])
        events[0].addSubscribedUser(users[1])
        events[0].addSubscribedUser(users[2])

        assertEquals(users.toMutableSet(), events[0].subscribedUsers)
    }

    @Test
    fun `Try attach course`() {
        assertEquals(courses[0], events[0].course)
    }

    @Test
    fun `Try quantity available`() {
        assertEquals(events[0].quantityAvailable(), 100)

        events[0].addSubscribedUser(users[0])
        events[0].addSubscribedUser(users[1])
        events[0].addSubscribedUser(users[2])

        assertEquals(events[0].quantityAvailable(), 97)
    }

    @Test
    fun `Try status`() {
        assertEquals(events[0].status(), "CONFIRMED")
    }
}