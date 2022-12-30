package com.example.fpandc13.data.network.models.ticket.list


import com.google.gson.annotations.SerializedName

data class Ticket(
    @SerializedName("airport")
    val airport: Airport? = null,
    @SerializedName("arrival_date")
    val arrivalDate: String? = null,
    @SerializedName("class")
    val classX: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("departure_date")
    val departureDate: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("id_airport")
    val idAirport: Int? = null,
    @SerializedName("price")
    val price: Int? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)