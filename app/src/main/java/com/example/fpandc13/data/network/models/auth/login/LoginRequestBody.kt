package com.example.fpandc13.data.network.models.auth.login


import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)