package com.example.fpandc13.data.network.models.auth.verify


import com.google.gson.annotations.SerializedName

data class VerifyRequestBody(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("token")
    val token: String?
)