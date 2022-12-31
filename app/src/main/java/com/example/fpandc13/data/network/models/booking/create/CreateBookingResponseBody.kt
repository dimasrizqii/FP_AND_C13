package com.example.fpandc13.data.network.models.booking.create


import com.google.gson.annotations.SerializedName

data class CreateBookingResponseBody(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("newBooking")
    val newBooking: NewBooking? = null
)