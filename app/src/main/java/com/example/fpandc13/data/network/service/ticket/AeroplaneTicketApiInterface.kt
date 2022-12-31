package com.example.fpandc13.data.network.service.ticket

import com.example.fpandc13.data.network.models.ticket.get.GetTicketResponse
import com.example.fpandc13.data.network.models.ticket.get.Ticket
import com.example.fpandc13.data.network.models.ticket.list.ListTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface AeroplaneTicketApiInterface {
    @GET("api/tickets")
    fun listTicket() : Call<TicketDetailResponse>

    @GET("api/tickets")
    fun getTicketAll() : Call<TicketDetailResponse>

    @GET("api/tickets/search")
     fun searchTicket(
        @Body searchTicketRequestBody: SearchTicketRequestBody
    ): SearchTicketResponse

    @GET("api/tickets/{id}")
     fun getTicket(@Path("id") id : Int?): TicketDetailResponse
}