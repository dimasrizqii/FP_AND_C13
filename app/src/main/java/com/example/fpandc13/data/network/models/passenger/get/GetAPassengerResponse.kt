package com.example.fpandc13.data.network.models.passenger.get


import com.google.gson.annotations.SerializedName

data class GetAPassengerResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("newPassenger")
    val newPassenger: NewPassenger? = null
)