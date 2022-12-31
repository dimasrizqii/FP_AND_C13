package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerRequestBody
import com.example.fpandc13.data.network.models.passenger.create.CreatePassengerResponse
import com.example.fpandc13.data.network.models.passenger.get.GetAPassengerResponse
import com.example.fpandc13.data.network.service.passenger.AeroplanePassengerApiInterface
import javax.inject.Inject

interface PassengerRemoteDataSource {
    suspend fun createPassenger(createPassengerRequestBody: CreatePassengerRequestBody): CreatePassengerResponse
    suspend fun getAPassenger(id: Int?): GetAPassengerResponse
}

class PassengerRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplanePassengerApiInterface) :
    PassengerRemoteDataSource {

    override suspend fun createPassenger(createPassengerRequestBody: CreatePassengerRequestBody): CreatePassengerResponse {
        return apiService.createPassenger(createPassengerRequestBody)
    }

    override suspend fun getAPassenger(id: Int?): GetAPassengerResponse {
        return apiService.getAPassenger(id)
    }

}