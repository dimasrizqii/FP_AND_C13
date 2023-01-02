package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.booking.history.HistoryBookingResponse
import com.example.fpandc13.data.network.models.booking.historyUser.BookingResponse
import com.example.fpandc13.data.network.models.booking.list.ListBookingResponse
import com.example.fpandc13.data.network.models.booking.listperuser.ListPerUserBookingResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.service.booking.AeroplaneBookingApiInterface
import com.example.fpandc13.ui.home.history.BookingState
import retrofit2.Call
import javax.inject.Inject

interface BookingRemoteDataSource {
    suspend fun createBooking(token : String, createBookingRequestBody: CreateBookingRequestBody): CreateBookingResponse
    suspend fun listBooking(): ListBookingResponse
    suspend fun listPerUserBooking(token : String): Call<BookingResponse>

}

class BookingRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplaneBookingApiInterface) :
    BookingRemoteDataSource {
    override suspend fun createBooking(token : String , createBookingRequestBody: CreateBookingRequestBody): CreateBookingResponse {
        return apiService.createBooking(token, createBookingRequestBody)
    }

    override suspend fun listBooking(): ListBookingResponse {
        return apiService.listBooking()
    }

    override suspend fun listPerUserBooking(token : String): Call<BookingResponse> {
        return apiService.getBookingUser(token)
    }

}