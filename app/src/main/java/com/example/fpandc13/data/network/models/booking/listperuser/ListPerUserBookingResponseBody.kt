package com.example.fpandc13.data.network.models.booking.listperuser


import com.google.gson.annotations.SerializedName

data class ListPerUserBookingResponseBody(
    @SerializedName("bookings")
    val bookings: Bookings? = null
)