package com.example.fpandc13.data.network.models.booking.listperuser


import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("nik")
    val nik: Any? = null,
    @SerializedName("passenger_name")
    val passengerName: Any? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)