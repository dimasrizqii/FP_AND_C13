package com.example.fpandc13.data.network.models.passenger.get


import com.google.gson.annotations.SerializedName

data class PassengerResponse(
    @SerializedName("passenger")
    val passenger: Passenger?
)