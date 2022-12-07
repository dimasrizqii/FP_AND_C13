package com.example.fpandc13.ui.home.profile

import org.junit.Assert.*
import org.junit.Test

class ProfileFragmentTest {

    private lateinit var profile: ProfileFragment

    @Test
    fun name_exist() {
        val result = profile.validateProfileFragmentInput("shawn","peter","123@test.com","12-3-4567", "098787654321")
        assertEquals("name exist",result,false)
    }

    @Test
    fun username_exist() {
        val result = profile.validateProfileFragmentInput("peter","raul","456@test.com","1-2-3456", "023445567890")
        assertEquals("username exist",result,false)
    }
}
