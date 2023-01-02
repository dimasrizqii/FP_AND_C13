package com.example.fpandc13.data.network.models.booking.historyUser


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Booking(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("id_passenger")
    val idPassenger: Int?,
    @SerializedName("id_ticket")
    val idTicket: Int?,
    @SerializedName("id_users")
    val idUsers: Int?,
    @SerializedName("passenger")
    val passenger: Passenger?,
    @SerializedName("ticket")
    val ticket: Ticket?,
    @SerializedName("total_booking")
    val totalBooking: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("User")
    val user: User?
) :Parcelable