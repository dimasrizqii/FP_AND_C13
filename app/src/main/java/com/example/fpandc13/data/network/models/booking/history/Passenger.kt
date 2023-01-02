package com.example.fpandc13.data.network.models.booking.history


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Passenger(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("nik")
    val nik: Int?,
    @SerializedName("passenger_name")
    val passengerName: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable