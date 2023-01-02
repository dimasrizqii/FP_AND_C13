package com.example.fpandc13.data.network.models.ticket.search


import com.example.fpandc13.data.network.models.ticket.list.detail.Ticket
import com.google.gson.annotations.SerializedName

data class SearchTicketResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("ticket")
    val ticket: List<Ticket>
)