package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerResponse
import com.example.fpandc13.data.network.models.passenger.get.PassengerResponse
import com.example.fpandc13.data.network.service.passenger.AeroplanePassengerApiInterface
import javax.inject.Inject

interface PassengerRemoteDataSource {
    suspend fun createPassenger(token : String , createPassengerRequestBody: CreatePassengerRequestBody): CreatePassengerResponse
    suspend fun getAPassenger(id: Int?): PassengerResponse
}

class PassengerRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplanePassengerApiInterface) :
    PassengerRemoteDataSource {

    override suspend fun createPassenger(token : String ,createPassengerRequestBody: CreatePassengerRequestBody): CreatePassengerResponse {
        return apiService.createPassenger(token ,createPassengerRequestBody)
    }

    override suspend fun getAPassenger(id: Int?): PassengerResponse {
        return apiService.getAPassenger(id)
    }

}