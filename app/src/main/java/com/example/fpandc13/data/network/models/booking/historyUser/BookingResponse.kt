package com.example.fpandc13.data.network.models.booking.historyUser


import com.google.gson.annotations.SerializedName
import retrofit2.Callback

data class BookingResponse(
    @SerializedName("bookings")
    val bookings: List<Booking>
) {
    fun enqueue(callback: Callback<BookingResponse>) {

    }
}