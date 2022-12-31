package com.example.fpandc13.data.network.service.passenger

import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerResponse
import com.example.fpandc13.data.network.models.passenger.get.PassengerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AeroplanePassengerApiInterface {
    @POST("api/passengers")
    suspend fun createPassenger(
        @Body createPassengerRequestBody: CreatePassengerRequestBody
    ): CreatePassengerResponse

    @GET("api/passengers/{id}")
    suspend fun getAPassenger(@Path("id") id: Int?): PassengerResponse
}