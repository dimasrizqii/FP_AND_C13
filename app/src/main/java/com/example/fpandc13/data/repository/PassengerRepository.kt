package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.PassengerRemoteDataSource
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerResponse
import com.example.fpandc13.data.network.models.passenger.get.GetAPassengerResponse
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface PassengerRepository {
    suspend fun createPassenger(createPassengerRequestBody: CreatePassengerRequestBody): Resource<CreatePassengerResponse>
    suspend fun getAPassenger(id : Int): Resource<GetAPassengerResponse>
}

class PassengerRepositoryImpl @Inject constructor(private val dataSource: PassengerRemoteDataSource) :
    PassengerRepository {

    override suspend fun createPassenger(createPassengerRequestBody: CreatePassengerRequestBody): Resource<CreatePassengerResponse> {
        return proceed {
            dataSource.createPassenger(createPassengerRequestBody)
        }
    }

    override suspend fun getAPassenger(id: Int): Resource<GetAPassengerResponse> {
        return proceed {
            dataSource.getAPassenger(id)
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