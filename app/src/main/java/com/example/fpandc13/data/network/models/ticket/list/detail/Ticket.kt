package com.example.fpandc13.data.network.models.ticket.list.detail


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("airport")
    val airport: Airport?,
    @SerializedName("arrival_date")
    val arrivalDate: String?,
    @SerializedName("class")
    val classX: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("departure_date")
    val departureDate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("id_airport")
    val idAirport: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)