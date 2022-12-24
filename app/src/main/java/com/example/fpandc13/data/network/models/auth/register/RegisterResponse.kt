package com.example.fpandc13.data.network.models.auth.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("user")
    val user: User? = null
)