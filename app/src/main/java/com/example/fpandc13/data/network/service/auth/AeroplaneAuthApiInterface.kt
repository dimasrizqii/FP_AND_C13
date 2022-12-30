package com.example.fpandc13.data.network.service.auth

import com.example.fpandc13.data.network.models.auth.login.LoginRequestBody
import com.example.fpandc13.data.network.models.auth.login.LoginResponse
import com.example.fpandc13.data.network.models.auth.profile.get.GetUserProfileResponse
import com.example.fpandc13.data.network.models.auth.profile.get.profile
import com.example.fpandc13.data.network.models.auth.profile.update.UpdateProfileResponse
import com.example.fpandc13.data.network.models.auth.register.RegisterRequestBody
import com.example.fpandc13.data.network.models.auth.register.RegisterResponse
import com.example.fpandc13.data.network.models.auth.verify.VerifyRequestBody
import com.example.fpandc13.data.network.models.auth.verify.VerifyResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @Multipart
    @PUT("api/auth/profile")
    fun updateUser(
        @Part("firstName") firstName: RequestBody,
        @Part("lastName") lastName: RequestBody,
        @Part("username") username: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
        @Part("address") address: RequestBody,
        @Part image: MultipartBody.Part ,
        @Header("Authorization") token: String): Call <profile>

    @GET("api/auth/profile")
    suspend fun getProfile(token : String): GetUserProfileResponse


    @PUT("api/auth/profile")
    suspend fun putProfile(token: String): UpdateProfileResponse
}