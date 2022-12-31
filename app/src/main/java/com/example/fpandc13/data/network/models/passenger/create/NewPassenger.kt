package com.example.fpandc13.data.network.models.passenger.create


import com.google.gson.annotations.SerializedName

data class NewPassenger(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("nik")
    val nik: String?,
    @SerializedName("passenger_name")
    val passengerName: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)