package com.example.fpandc13.data.network.models.passenger.create


import com.google.gson.annotations.SerializedName

data class NewPassenger(
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("nik")
    val nik: String? = null,
    @SerializedName("passenger_name")
    val passengerName: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)