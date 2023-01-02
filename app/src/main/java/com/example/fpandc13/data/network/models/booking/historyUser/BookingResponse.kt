package com.example.fpandc13.data.network.models.booking.historyUser


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.Callback

@Parcelize
data class BookingResponse(
    @SerializedName("bookings")
    val bookings: List<Booking>
) : Parcelable