package com.example.fpandc13.data.network.service.auth

import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.get.GetProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.data.network.models.auth.register.RegisterResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.data.network.models.auth.verify.VerifyResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AeroplaneAuthApiInterface {
    @POST("api/auth/register")
    suspend fun postRegister(
        @Body registerRequestBody: RegisterRequestBody
    ): RegisterResponse

    @POST("api/auth/login")
    suspend fun postLogin(
        @Body loginRequestBody: LoginRequestBody
    ): LoginResponse

    @POST("api/auth/verify")
    suspend fun postVerify(
        @Body verifyRequestBody: VerifyRequestBody
    ): VerifyResponse

    @GET("api/auth/profile")
    suspend fun getProfile(token: String): GetProfileResponse

    @PUT("api/auth/profile")
    suspend fun putProfile(token: String): UpdateProfileResponse
}