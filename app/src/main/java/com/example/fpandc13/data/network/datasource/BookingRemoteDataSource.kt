package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.booking.list.ListBookingResponse
import com.example.fpandc13.data.network.models.booking.listperuser.ListPerUserBookingResponse
import com.example.fpandc13.data.network.service.booking.AeroplaneBookingApiInterface
import javax.inject.Inject

interface BookingRemoteDataSource {
    suspend fun createBooking(token : String, createBookingRequestBody: CreateBookingRequestBody): CreateBookingResponse
    suspend fun listBooking(): ListBookingResponse
    suspend fun listPerUserBooking(): ListPerUserBookingResponse
}

class BookingRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplaneBookingApiInterface) :
    BookingRemoteDataSource {
    override suspend fun createBooking(token : String , createBookingRequestBody: CreateBookingRequestBody): CreateBookingResponse {
        return apiService.createBooking(token, createBookingRequestBody)
    }

    override suspend fun listBooking(): ListBookingResponse {
        return apiService.listBooking()
    }

    override suspend fun listPerUserBooking(): ListPerUserBookingResponse {
        return apiService.listPerUserBooking()
    }

}