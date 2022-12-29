package com.example.fpandc13.data.network.models.booking.list


import com.google.gson.annotations.SerializedName

data class ListBookingResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("data")
    val `data`: String? = null
)