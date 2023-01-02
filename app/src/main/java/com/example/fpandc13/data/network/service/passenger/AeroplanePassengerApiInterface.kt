package com.example.fpandc13.data.network.service.passenger

import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerResponse
import com.example.fpandc13.data.network.models.passenger.get.PassengerResponse
import retrofit2.http.*

interface AeroplanePassengerApiInterface {
    @POST("api/passengers")
    suspend fun createPassenger(
        @Header("Authorization") token: String,
        @Body createPassengerRequestBody: CreatePassengerRequestBody
    ): CreatePassengerResponse

    @GET("api/passengers/{id}")
    suspend fun getAPassenger(@Path("id") id: Int?): PassengerResponse
}