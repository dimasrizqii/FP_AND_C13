package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.BookingRemoteDataSource
import com.example.fpandc13.data.network.models.booking.create.CreateBookingRequestBody
import com.example.fpandc13.data.network.models.booking.create.CreateBookingResponse
import com.example.fpandc13.data.network.models.booking.history.HistoryBookingResponse
import com.example.fpandc13.data.network.models.booking.historyUser.BookingResponse
import com.example.fpandc13.data.network.models.booking.list.ListBookingResponse
import com.example.fpandc13.data.network.models.booking.listperuser.ListPerUserBookingResponse
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface BookingRepository {
    suspend fun createBooking(token : String , createBookingRequestBody: CreateBookingRequestBody): Resource<CreateBookingResponse>
    suspend fun listBooking(): Resource<ListBookingResponse>
    suspend fun listPerUserBooking(token : String): Resource<BookingResponse>
}

class BookingRepositoryImpl @Inject constructor(private val dataSource: BookingRemoteDataSource) :
    BookingRepository {

    override suspend fun createBooking(token : String , createBookingRequestBody: CreateBookingRequestBody): Resource<CreateBookingResponse> {
        return proceed {
            dataSource.createBooking(token , createBookingRequestBody)
        }
    }

    override suspend fun listBooking(): Resource<ListBookingResponse> {
        return proceed {
            dataSource.listBooking()
        }
    }

    override suspend fun listPerUserBooking(token : String): Resource<BookingResponse> {
        return proceed {
            dataSource.listPerUserBooking(token)
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