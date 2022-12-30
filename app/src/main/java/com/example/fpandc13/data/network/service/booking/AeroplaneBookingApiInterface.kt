package com.example.fpandc13.data.network.service.booking

import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.booking.list.ListBookingResponseBody
import com.example.fpandc13.data.network.models.booking.listperuser.ListPerUserBookingResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AeroplaneBookingApiInterface {
    @POST("api/bookings")
    suspend fun createBooking(
        @Body createBookingRequestBody: CreateBookingRequestBody
    ): CreateBookingResponse

    @GET("api/bookings")
    suspend fun listBooking(): ListBookingResponseBody

    @GET("api/bookings/user")
    suspend fun listPerUserBooking(): ListPerUserBookingResponseBody
}