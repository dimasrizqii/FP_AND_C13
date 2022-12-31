package com.example.fpandc13.data.network.models.booking.listperuser


import com.google.gson.annotations.SerializedName

data class Bookings(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("bookings")
    val bookings: List<Booking?>? = null,
    @SerializedName("country_code")
    val countryCode: Any? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("lastName")
    val lastName: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("photo")
    val photo: String? = null,
    @SerializedName("role")
    val role: Int? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
    @SerializedName("username")
    val username: String? = null
)