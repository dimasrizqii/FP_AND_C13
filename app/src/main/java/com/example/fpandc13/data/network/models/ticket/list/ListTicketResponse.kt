package com.example.fpandc13.data.network.models.ticket.list


import com.google.gson.annotations.SerializedName

data class ListTicketResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("data")
    val `data`: String? = null
)