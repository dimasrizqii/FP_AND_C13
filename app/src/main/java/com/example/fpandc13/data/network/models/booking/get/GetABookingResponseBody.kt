package com.example.fpandc13.data.network.models.booking.get


import com.google.gson.annotations.SerializedName

data class GetABookingResponseBody(
    @SerializedName("data")
    val `data`: Data? = null
)