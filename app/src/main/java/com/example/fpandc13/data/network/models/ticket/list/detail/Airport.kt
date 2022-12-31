package com.example.fpandc13.data.network.models.ticket.list.detail


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Airport(
    @SerializedName("airport_location")
    val airportLocation: String?,
    @SerializedName("airport_name")
    val airportName: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable