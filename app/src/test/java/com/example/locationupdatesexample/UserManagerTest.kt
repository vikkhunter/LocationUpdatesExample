package com.example.locationupdatesexample

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class UserManagerTest {

    @Test
    fun testSaveUser() {
        val userManager = mockk<UserManager>(relaxed = true)
        val userSlot = slot<User>() //create slot of type argument
        val expectedUser = User("username", "email@example.com")
        someFunctionThatCallsSaveUser(userManager)
        verify { userManager.saveUser(capture(userSlot)) }
        assertEquals(expectedUser.username, userSlot.captured.username)
        assertEquals(expectedUser.email, userSlot.captured.email)
    }

    private fun someFunctionThatCallsSaveUser(userManager: UserManager) {
        userManager.saveUser(User("username", "email@example.com"))
    }

    @Test
    fun testSpecificArguments() {
        val mockedClass = mockk<CheckNumber>()
        every { mockedClass.calculateValue(less(50)) } returns "Smaller Number"
        every { mockedClass.calculateValue(more(50)) } returns "Large Number"
        every { mockedClass.calculateValue(eq(50)) } returns "Fifty"

        val result = mockedClass.calculateValue(50)
        assertEquals("LargeNumber", result)
    }
}

class CheckNumber {
    fun calculateValue(less: Int) : String {
        return less.toString()
    }
}
