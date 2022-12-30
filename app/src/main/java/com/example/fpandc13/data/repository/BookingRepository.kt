package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.BookingRemoteDataSource
import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.booking.list.ListBookingResponseBody
import com.example.fpandc13.data.network.models.booking.listperuser.ListPerUserBookingResponseBody
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface BookingRepository {
    suspend fun createBooking(createBookingRequestBody: CreateBookingRequestBody): Resource<CreateBookingResponse>
    suspend fun listBooking(): Resource<ListBookingResponseBody>
    suspend fun listPerUserBooking(): Resource<ListPerUserBookingResponseBody>
}

class BookingRepositoryImpl @Inject constructor(private val dataSource: BookingRemoteDataSource) :
    BookingRepository {

    override suspend fun createBooking(createBookingRequestBody: CreateBookingRequestBody): Resource<CreateBookingResponse> {
        return proceed {
            dataSource.createBooking(createBookingRequestBody)
        }
    }

    override suspend fun listBooking(): Resource<ListBookingResponseBody> {
        return proceed {
            dataSource.listBooking()
        }
    }

    override suspend fun listPerUserBooking(): Resource<ListPerUserBookingResponseBody> {
        return proceed {
            dataSource.listPerUserBooking()
        }
    }

    private suspend fun <T> proceed(coroutines: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutines.invoke())
        } catch (e: Exception) {
            Resource.Error(e, e.message)
        }
    }
}