package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.booking.get.GetABookingResponseBody
import com.example.fpandc13.data.network.models.booking.listperuser.ListPerUserBookingResponseBody
import com.example.fpandc13.data.network.service.booking.AeroplaneBookingApiInterface
import javax.inject.Inject

interface BookingRemoteDataSource {
    suspend fun createBooking(createBookingRequestBody: CreateBookingRequestBody): CreateBookingResponse
    suspend fun listPerUserBooking(): ListPerUserBookingResponseBody
    suspend fun getABooking(id: Int?): GetABookingResponseBody
}

class BookingRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplaneBookingApiInterface) :
    BookingRemoteDataSource {
    override suspend fun createBooking(createBookingRequestBody: CreateBookingRequestBody): CreateBookingResponse {
        return apiService.createBooking(createBookingRequestBody)
    }

    override suspend fun listPerUserBooking(): ListPerUserBookingResponseBody {
        return apiService.listPerUserBooking()
    }

    override suspend fun getABooking(id: Int?): GetABookingResponseBody {
        return apiService.getABooking(id)
    }

}