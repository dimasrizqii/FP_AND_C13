package com.example.fpandc13.data.network.models.booking.history


import com.google.gson.annotations.SerializedName

data class HistoryBookingResponse(
    @SerializedName("bookings")
    val bookings: List<Booking?>?
)