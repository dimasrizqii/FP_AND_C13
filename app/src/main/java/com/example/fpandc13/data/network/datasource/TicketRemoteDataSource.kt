package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.ticket.get.GetTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.ListTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketResponse
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import retrofit2.Call
import javax.inject.Inject

interface TicketRemoteDataSource {
    suspend fun listTicket(): Call<TicketDetailResponse>
    suspend fun searchTicket(token : String,airport : String,airportlocation : String,departure : String,arrival : String,price: Int,kelas : String): SearchTicketResponse
    suspend fun getTicket(id: Int?): TicketDetailResponse
}

class TicketRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplaneTicketApiInterface) :
    TicketRemoteDataSource {

    override suspend fun listTicket(): Call<TicketDetailResponse> {
        return apiService.listTicket()
    }

    override suspend fun searchTicket(
        token: String,
        airport: String,
        airportlocation: String,
        departure: String,
        arrival: String,
        price: Int,
        kelas: String
    ): SearchTicketResponse {
        return apiService.searchTicket(token,airport,airportlocation,departure,arrival, price, kelas)
    }

    override suspend fun getTicket(id: Int?): TicketDetailResponse {
        return apiService.getTicket(id)
    }

}