package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.AuthRemoteDataSource
import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.data.network.models.auth.register.RegisterResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.data.network.models.auth.verify.VerifyResponse
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface AuthRepository {

    suspend fun postRegisterUser(registerRequestBody: RegisterRequestBody): Resource<RegisterResponse>
    suspend fun postLoginUser(loginRequestBody: LoginRequestBody): Resource<LoginResponse>
    suspend fun postVerifyUser(verifyRequestBody: VerifyRequestBody): Resource<VerifyResponse>
}

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthRemoteDataSource) :
    AuthRepository {


    override suspend fun postRegisterUser(registerRequestBody: RegisterRequestBody): Resource<RegisterResponse> {
        return proceed {
            dataSource.postRegister(registerRequestBody)
        }
    }

    override suspend fun postLoginUser(loginRequestBody: LoginRequestBody): Resource<LoginResponse> {
        return proceed {
            dataSource.postLogin(loginRequestBody)
        }
    }

    override suspend fun postVerifyUser(verifyRequestBody: VerifyRequestBody): Resource<VerifyResponse> {
        return proceed {
            dataSource.postVerify(verifyRequestBody)
        }
    }

    private suspend fun <T> proceed(coroutines: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutines.invoke())
        } catch (e: Exception) {
            Resource.Error(e, e.message)
        }/* catch (httpE: HttpException) {
            val response = httpE.response()?.errorBody()?.string()
            Resource.Error(httpE, httpE.response()?.message())
        }*/
    }
}