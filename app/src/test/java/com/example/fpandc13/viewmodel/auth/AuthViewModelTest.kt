package com.example.fpandc13.viewmodel.auth

import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.data.network.models.auth.register.RegisterResponse
import com.example.fpandc13.data.network.service.auth.AeroplaneAuthApiInterface
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AuthViewModelTest {
    private lateinit var service: AeroplaneAuthApiInterface

    @Before
    fun setUp(){
        service = mockk()
    }

    @Test
    fun postLoginViewModelTest(): Unit = runBlocking {
        val respAuthLogin = mockk <LoginResponse>()

        every {
            runBlocking {
                service.postLogin(LoginRequestBody("test", "test"))
            }
        } returns respAuthLogin

        val result = service.postLogin(LoginRequestBody("test", "test"))

        verify {
            runBlocking {
                service.postLogin(LoginRequestBody("test", "test"))
            }
        }
        assertEquals(result, respAuthLogin)
    }

    @Test
    fun postRegisterViewModelTest(): Unit = runBlocking {
        val respAuthRegister= mockk <RegisterResponse>()

        every {
            runBlocking {
                service.postRegister(RegisterRequestBody("test", "test", "test", "test", "test", "test", "test", "test", 1, "test"))
            }
        } returns respAuthRegister

        val result = service.postRegister(RegisterRequestBody("test", "test", "test", "test", "test", "test", "test", "test", 1, "test"))

        verify {
            runBlocking {
                service.postRegister(RegisterRequestBody("test", "test", "test", "test", "test", "test", "test", "test", 1, "test"))
            }
        }
        assertEquals(result, respAuthRegister)
    }

    @Test
    fun postProfileViewModelTest(): Unit = runBlocking {
        val respUpdateProfile= mockk <UpdateProfileResponse>()

        every {
            runBlocking {
                service.putProfile("1")
            }
        } returns respUpdateProfile

        val result = service.putProfile("1")

        verify {
            runBlocking {
                service.putProfile("1")
            }
        }
        assertEquals(result, respUpdateProfile)
    }

}
