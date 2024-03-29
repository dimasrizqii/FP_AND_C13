package com.example.fpandc13.data.network.models.passenger.create


import com.google.gson.annotations.SerializedName

data class CreatePassengerResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("newPassenger")
    val newPassenger: NewPassenger?
)