package com.example.fpandc13.data.network.models.ticket.list.detail


import com.google.gson.annotations.SerializedName

data class TicketDetailResponse(
    @SerializedName("tickets")
    val tickets: List<Ticket?>?
//    ,
//    @SerializedName("message")
//    var message: String?,
//    @SerializedName("status")
//    var status: String?
)