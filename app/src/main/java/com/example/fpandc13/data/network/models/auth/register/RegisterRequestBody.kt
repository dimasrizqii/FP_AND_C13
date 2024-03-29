package com.example.fpandc13.data.network.models.auth.register


import com.google.gson.annotations.SerializedName

data class RegisterRequestBody(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("country_code")
    val countryCode: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("firstName")
    val firstName: String? = null,
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
    @SerializedName("username")
    val username: String? = null
)