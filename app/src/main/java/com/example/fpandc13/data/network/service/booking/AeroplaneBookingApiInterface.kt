package com.example.fpandc13.data.network.service.booking

import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.booking.historyUser.BookingResponse
import com.example.fpandc13.data.network.models.booking.list.ListBookingResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AeroplaneBookingApiInterface {
    @POST("api/bookings")
    suspend fun createBooking(
        @Header("Authorization") token: String,
        @Body createBookingRequestBody: CreateBookingRequestBody
    ): CreateBookingResponse

    @GET("api/bookings")
    suspend fun listBooking(): ListBookingResponse

    @GET("api/bookings/user")
    suspend fun listPerUserBooking(
        @Header("Authorization") token: String
    ): BookingResponse

    @GET("api/bookings/user")
    fun getBookingUser(
        @Header("Authorization") token: String)
    : Call<BookingResponse>


    @GET("api/bookings/user")
    fun getHistoryBooking(
        @Header("Authorization") token: String)
    : Call<BookingResponse>
}