package com.example.fpandc13.data.network.models.booking.historyUser


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(
    @SerializedName("arrival_date")
    val arrivalDate: String?,
    @SerializedName("class")
    val classX: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("departure_date")
    val departureDate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("id_airport")
    val idAirport: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable