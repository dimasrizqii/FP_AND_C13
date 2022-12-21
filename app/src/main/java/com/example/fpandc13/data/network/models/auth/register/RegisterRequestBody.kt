package com.example.fpandc13.data.network.models.auth.register


import com.google.gson.annotations.SerializedName

data class RegisterRequestBody(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)