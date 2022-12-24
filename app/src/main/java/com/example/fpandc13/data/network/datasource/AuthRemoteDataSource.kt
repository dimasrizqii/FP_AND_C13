package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.get.GetProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.data.network.models.auth.register.RegisterResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.data.network.models.auth.verify.VerifyResponse
import com.example.fpandc13.data.network.service.auth.AeroplaneAuthApiInterface
import javax.inject.Inject

interface AuthRemoteDataSource {
    suspend fun getProfile(token: String): GetProfileResponse
    suspend fun putProfile(token: String): UpdateProfileResponse

    suspend fun postRegister(registerRequestBody: RegisterRequestBody): RegisterResponse
    suspend fun postLogin(loginRequestBody: LoginRequestBody): LoginResponse
    suspend fun postVerify(VerifyRequestBody: VerifyRequestBody): VerifyResponse
}

class AuthRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplaneAuthApiInterface) :
    AuthRemoteDataSource {

    override suspend fun getProfile(token: String): GetProfileResponse {
        return apiService.getProfile(token)
    }

    override suspend fun putProfile(token: String): UpdateProfileResponse {
        return apiService.putProfile(token)
    }

    override suspend fun postRegister(registerRequestBody: RegisterRequestBody): RegisterResponse {
        return apiService.postRegister(registerRequestBody)
    }
    override suspend fun postLogin(loginRequestBody: LoginRequestBody): LoginResponse {
        return apiService.postLogin(loginRequestBody)
    }

    override suspend fun postVerify(VerifyRequestBody: VerifyRequestBody): VerifyResponse {
        return apiService.postVerify(VerifyRequestBody)
    }


}