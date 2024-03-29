package com.example.fpandc13.data.network.models.auth.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("token")
    val token: String? = null
)