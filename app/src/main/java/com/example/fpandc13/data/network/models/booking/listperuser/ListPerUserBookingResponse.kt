package com.example.fpandc13.data.network.models.booking.listperuser


import com.google.gson.annotations.SerializedName

data class ListPerUserBookingResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("data")
    val `data`: String? = null
)