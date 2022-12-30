package com.example.fpandc13.data.network.models.ticket.list.detail


import com.google.gson.annotations.SerializedName

data class Airport(
    @SerializedName("airport_location")
    val airportLocation: String?,
    @SerializedName("airport_name")
    val airportName: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)