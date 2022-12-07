package com.example.fpandc13.ui.register

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegisterFragmentTest {

    private lateinit var register: RegisterFragment

    @Before
    fun setUp(){
        register = RegisterFragment()
    }

    @Test
    fun email_exist() {
        val result = register.validateRegisterFragmentInput("shawn","123456","123456","shawn@test.com")
        assertEquals("email exist",result,false)
    }

    @Test
    fun username_exist() {
        val result = register.validateRegisterFragmentInput("peter","123456","123456","peter@test.com")
        assertEquals("username exist",result,false)
    }

    @Test
    fun username_empty() {
        val result = register.validateRegisterFragmentInput("","123456","123456","123456@test.com")
        assertEquals("username empty",result,false)
    }

    @Test
    fun password_kurang() {
        val result = register.validateRegisterFragmentInput("mendes","","12345","12345@test.com")
        assertEquals("password < 6",result,false)
    }

    @Test
    fun password_lebih() {
        val result = register.validateRegisterFragmentInput("shawny","123453121212121212121212121212121212121212121212121212121212123213231123","123453121212121212121212121212121212121212121212121212121212123213231123", "20@test.com")
        assertEquals("password > 50",result,false)
    }


    @Test
    fun email_empty() {
        val result = register.validateRegisterFragmentInput("raul","123456","123456","")
        assertEquals("email empty",result,false)
    }
}

