package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.TicketRemoteDataSource
import com.example.fpandc13.data.network.models.ticket.get.GetTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.ListTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketResponse
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface TicketRepository {
    suspend fun getTicket(id: Int): Resource<TicketDetailResponse>
    suspend fun searchTicket(searchTicketRequestBody: SearchTicketRequestBody): Resource<SearchTicketResponse>
}

class TicketRepositoryImpl @Inject constructor(private val dataSource: TicketRemoteDataSource) :
    TicketRepository {


    suspend fun <T> proceed(coroutines: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutines.invoke())
        } catch (e: Exception) {
            Resource.Error(e, e.message)
        }
    }



    override suspend fun getTicket(id: Int): Resource<TicketDetailResponse> {
        return proceed {
            dataSource.getTicket(id)
        }
    }

    override suspend fun searchTicket(searchTicketRequestBody: SearchTicketRequestBody): Resource<SearchTicketResponse> {
        return proceed {
            dataSource.searchTicket(searchTicketRequestBody)
        }
    }
}