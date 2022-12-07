package com.example.fpandc13.ui.login

import org.junit.Assert.*
import org.junit.Test

class LoginFragmentTest {

    private lateinit var login: LoginFragment

    @Test
    fun username_exist() {
        val result = login.validateLoginFragmentInput("peter","123456")
        assertEquals("username exist",result,false)
    }


    @Test
    fun password_kurang() {
        val result = login.validateLoginFragmentInput("peter","123")
        assertEquals("password < 6",result,false)
    }

}