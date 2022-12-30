package com.example.fpandc13.data.network.models.ticket.list


import com.google.gson.annotations.SerializedName

data class ListTicketResponseBody(
    @SerializedName("tickets")
    val tickets: List<Ticket?>? = null
)