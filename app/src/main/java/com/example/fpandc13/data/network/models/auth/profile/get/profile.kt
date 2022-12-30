package com.example.fpandc13.data.network.models.auth.profile.get


import com.google.gson.annotations.SerializedName

data class profile(

    @SerializedName("address")
    val address: String?,
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: String?,
    @SerializedName("lastName")
    val lastName: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("role")
    val role: Int?,
    @SerializedName("username")
    val username: String?
)