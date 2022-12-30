package com.example.fpandc13.data.network.models.booking.list


import com.google.gson.annotations.SerializedName

data class ListBookingResponseBody(
    @SerializedName("bookings")
    val bookings: List<Booking?>? = null
)