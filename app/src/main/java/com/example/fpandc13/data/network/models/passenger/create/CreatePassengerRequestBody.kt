package com.example.fpandc13.data.network.models.passenger.create


import com.google.gson.annotations.SerializedName

data class CreatePassengerRequestBody(
    @SerializedName("nik")
    val nik: String? = null,
    @SerializedName("passenger_name")
    val passengerName: String? = null
)