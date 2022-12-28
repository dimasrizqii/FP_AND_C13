package com.example.fpandc13.data.network.service.auth

import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.get.GetUserProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.data.network.models.auth.register.RegisterResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.data.network.models.auth.verify.VerifyResponse
import retrofit2.Call
import retrofit2.http.*


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
    suspend fun getProfileUser(
        @Header("Authorization") token: String
    ): GetUserProfileResponse

//    @GET("api/auth/profile")
//    fun getProfileUser(
//        @Header("Authorization") token: String ): Call<GetUserProfileResponse?>?


    @POST("refreshToken")
    fun refreshToken(refreshToken: String):
            Call<GetUserProfileResponse>

    @GET("api/auth/profile")
    suspend fun getProfile(token : String): GetUserProfileResponse


    @PUT("api/auth/profile")
    suspend fun putProfile(token: String): UpdateProfileResponse
}