package com.example.fpandc13.data.network.models.ticket.get


import com.google.gson.annotations.SerializedName

data class GetTicketResponse(
    @SerializedName("ticket")
    val ticket: Ticket? = null
)