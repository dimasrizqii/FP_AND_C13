package com.example.fpandc13.data.network.service.ticket

import com.example.fpandc13.data.network.models.ticket.get.GetTicketResponse
import com.example.fpandc13.data.network.models.ticket.get.Ticket
import com.example.fpandc13.data.network.models.ticket.list.ListTicketResponse
import com.example.fpandc13.data.network.models.ticket.list.detail.TicketDetailResponse
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketRequestBody
import com.example.fpandc13.data.network.models.ticket.search.SearchTicketResponse
import retrofit2.Call
import retrofit2.http.*

interface AeroplaneTicketApiInterface {
    @GET("api/tickets")
    fun listTicket() : Call<TicketDetailResponse>

    @GET("api/tickets")
    fun getTicketAll() : Call<TicketDetailResponse>

    @GET("api/tickets/search")
     fun searchTicket(
        @Header("Authorization") token: String,
        @Query("airport") airport: String = "",
        @Query("airportloc") airportLoc: String= "",
        @Query("departure") departure: String= "",
        @Query("arrival") arrival: String= "",
        @Query("price") price: Int =0,
        @Query("class") kelas: String = ""

    ): SearchTicketResponse

    @GET("api/tickets/{id}")
     fun getTicket(@Path("id") id : Int?): TicketDetailResponse
}