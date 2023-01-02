package com.example.fpandc13.data.network.models.ticket.search


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchTicketRequestBody(
    @SerializedName("airport_location")
    val airportLocation: String,
    @SerializedName("airport_name")
    val airportName: String,
    @SerializedName("arrival_date")
    val arrivalDate: String,
    @SerializedName("class")
    val classX: String,
    @SerializedName("departure_date")
    val departureDate: String,
    @SerializedName("price")
    val price: Int
) : Parcelable