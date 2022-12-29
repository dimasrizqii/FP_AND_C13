package com.example.fpandc13.data.repository

import com.example.fpandc13.data.network.datasource.TicketRemoteDataSource
import com.example.fpandc13.data.network.models.ticket.get.GetTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.ListTicketResponse
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketResponse
import com.example.fpandc13.wrapper.Resource
import javax.inject.Inject

interface TicketRepository {
    suspend fun listTicket(): Resource<ListTicketResponse>
    suspend fun getTicket(id: Int): Resource<GetTicketResponse>
    suspend fun searchTicket(searchTicketRequestBody: SearchTicketRequestBody): Resource<SearchTicketResponse>
}

class TicketRepositoryImpl @Inject constructor(private val dataSource: TicketRemoteDataSource) :
    TicketRepository {
    override suspend fun listTicket(): Resource<ListTicketResponse> {
        return proceed {
            dataSource.listTicket()
        }
    }

    override suspend fun getTicket(id: Int): Resource<GetTicketResponse> {
        return proceed {
            dataSource.getTicket(id)
        }
    }

    override suspend fun searchTicket(searchTicketRequestBody: SearchTicketRequestBody): Resource<SearchTicketResponse> {
        return proceed {
            dataSource.searchTicket(searchTicketRequestBody)
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