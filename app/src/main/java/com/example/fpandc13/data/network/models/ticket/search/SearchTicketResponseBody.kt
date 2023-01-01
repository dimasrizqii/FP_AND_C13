package com.example.fpandc13.data.network.models.ticket.search


import com.google.gson.annotations.SerializedName

data class SearchTicketResponseBody(
    @SerializedName("tickets")
    val tickets: List<Ticket?>? = null
)