package com.example.fpandc13.data.network.models.booking.create


import com.google.gson.annotations.SerializedName

data class CreateBookingRequestBody(
    @SerializedName("id_passenger")
    val idPassenger: Int? = null,
    @SerializedName("id_ticket")
    val idTicket: Int? = null,
    @SerializedName("id_users")
    val idUsers: Int? = null,
    @SerializedName("total_booking")
    val totalBooking: Int? = null
)