package com.example.fpandc13.data.network.models.ticket.search


import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("airport_location")
    val airportLocation: String? = null,
    @SerializedName("airport_name")
    val airportName: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)