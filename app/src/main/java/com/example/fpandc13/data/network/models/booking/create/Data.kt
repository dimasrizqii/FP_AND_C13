package com.example.fpandc13.data.network.models.booking.create


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("id_passenger")
    val idPassenger: Int? = null,
    @SerializedName("id_ticket")
    val idTicket: Int? = null,
    @SerializedName("id_users")
    val idUsers: Int? = null,
    @SerializedName("total_booking")
    val totalBooking: Int? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)