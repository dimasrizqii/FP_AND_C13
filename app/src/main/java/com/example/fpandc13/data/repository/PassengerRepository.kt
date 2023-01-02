package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.PassengerRemoteDataSource
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerResponse
import com.example.fpandc13.data.network.models.passenger.get.PassengerResponse
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface PassengerRepository {
    suspend fun createPassenger(token : String ,createPassengerRequestBody: CreatePassengerRequestBody): Resource<CreatePassengerResponse>
    suspend fun Passenger(id : Int): Resource<PassengerResponse>
}

class PassengerRepositoryImpl @Inject constructor(private val dataSource: PassengerRemoteDataSource) :
    PassengerRepository {



    private suspend fun <T> proceed(coroutines: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutines.invoke())
        } catch (e: Exception) {
            Resource.Error(e, e.message)
        }
    }

    override suspend fun createPassenger(token : String ,createPassengerRequestBody: CreatePassengerRequestBody): Resource<CreatePassengerResponse> {
        return proceed {
            dataSource.createPassenger(token , createPassengerRequestBody)
        }
    }

    override suspend fun Passenger(id: Int): Resource<PassengerResponse> {
        return proceed {
            dataSource.getAPassenger(id)
        }
    }
}