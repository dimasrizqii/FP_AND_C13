package com.example.fpandc13.data.network.service.ticket

import com.example.fpandc13.data.network.models.ticket.get.GetTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.ListTicketResponseBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface AeroplaneTicketApiInterface {
    @GET("api/tickets")
    suspend fun listTicket() : ListTicketResponseBody

    @GET("api/tickets/search")
    suspend fun searchTicket(
        @Body searchTicketRequestBody: SearchTicketRequestBody
    ): SearchTicketResponse

    @GET("api/tickets/{id}")
    suspend fun getTicket(@Path("id") id : Int?): GetTicketResponse
}