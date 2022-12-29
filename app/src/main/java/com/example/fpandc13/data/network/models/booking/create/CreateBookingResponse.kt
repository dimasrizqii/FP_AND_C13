package com.example.fpandc13.data.network.models.booking.create


import com.google.gson.annotations.SerializedName

data class CreateBookingResponse(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: String? = null
)