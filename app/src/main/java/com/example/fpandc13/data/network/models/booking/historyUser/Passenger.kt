package com.example.fpandc13.data.network.models.booking.historyUser


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
    val nik: Long?,
    @SerializedName("passenger_name")
    val passengerName: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable