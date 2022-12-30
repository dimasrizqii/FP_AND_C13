package com.example.fpandc13.data.network.datasource

import com.example.fpandc13.data.network.models.ticket.get.GetTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.ListTicketResponseBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketResponse
import com.example.fpandc13.data.network.service.ticket.AeroplaneTicketApiInterface
import javax.inject.Inject

interface TicketRemoteDataSource {
    suspend fun listTicket(): ListTicketResponseBody
    suspend fun searchTicket(searchTicketRequestBody: SearchTicketRequestBody): SearchTicketResponse
    suspend fun getTicket(id: Int?): GetTicketResponse
}

class TicketRemoteDataSourceImpl @Inject constructor(private val apiService: AeroplaneTicketApiInterface) :
    TicketRemoteDataSource {

    override suspend fun listTicket(): ListTicketResponseBody {
        return apiService.listTicket()
    }

    override suspend fun searchTicket(searchTicketRequestBody: SearchTicketRequestBody): SearchTicketResponse {
        return apiService.searchTicket(searchTicketRequestBody)
    }

    override suspend fun getTicket(id: Int?): GetTicketResponse {
        return apiService.getTicket(id)
    }

}