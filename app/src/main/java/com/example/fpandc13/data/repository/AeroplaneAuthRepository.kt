package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.AeroplaneAuthDataSource
import com.example.fpandc13.models.auth.login.LoginRequestBody
import com.example.fpandc13.models.auth.login.LoginResponse
import com.example.fpandc13.models.auth.register.RegisterRequestBody
import com.example.fpandc13.models.auth.register.RegisterResponse
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface AeroplaneAuthRepository {
    suspend fun postRegisterRepository(registerRequestBody: RegisterRequestBody): Resource<RegisterResponse>
    suspend fun postLoginRepository(loginRequestBody: LoginRequestBody) : Resource<LoginResponse>
}

class AeroplaneAuthRepositoryImpl @Inject constructor(private val dataSource: AeroplaneAuthDataSource) :
    AeroplaneAuthRepository {
    override suspend fun postRegisterRepository(registerRequestBody: RegisterRequestBody): Resource<RegisterResponse> {
        return proceed {
            dataSource.postRegisterDataSource(registerRequestBody)
        }
    }

    override suspend fun postLoginRepository(loginRequestBody: LoginRequestBody): Resource<LoginResponse> {
        return proceed {
            dataSource.postLoginDataSource(loginRequestBody)
        }
    }

    private suspend fun <T> proceed(coroutines: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutines.invoke())
        } catch (e: Exception) {
            Resource.Error(e, e.message)
        }
    }
}