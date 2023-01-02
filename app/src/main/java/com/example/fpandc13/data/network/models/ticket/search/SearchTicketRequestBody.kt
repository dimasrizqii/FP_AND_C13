package com.example.fpandc13.data.network.models.ticket.search


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchTicketRequestBody(
    @SerializedName("airport_location")
    val airportLocation: String? = null,
    @SerializedName("airport_name")
    val airportName: String? = null,
    @SerializedName("arrival_date")
    val arrivalDate: String? = null,
    @SerializedName("class")
    val classX: String? = null,
    @SerializedName("departure_date")
    val departureDate: String? = null,
    @SerializedName("price")
    val price: Int? = null
) : Parcelable