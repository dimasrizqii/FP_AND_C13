package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.models.auth.login.LoginResponse
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import com.example.fpandc13.service.auth.AeroplaneAuthApiInterface
import javax.inject.Inject

interface AeroplaneAuthDataSource {
    suspend fun postRegisterDataSource(registerRequestBody: RegisterRequestBody) : RegisterResponse
    suspend fun postLoginDataSource(loginRequestBody: LoginRequestBody) : LoginResponse
}

class AeroplaneAuthDataSourceImpl @Inject constructor(private val apiService: AeroplaneAuthApiInterface) :
        AeroplaneAuthDataSource {
    override suspend fun postRegisterDataSource(registerRequestBody: RegisterRequestBody): RegisterResponse {
        return apiService.postRegister(registerRequestBody)
    }

    override suspend fun postLoginDataSource(loginRequestBody: LoginRequestBody): LoginResponse {
        return apiService.postLogin(loginRequestBody)
    }

}