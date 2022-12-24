package com.example.fpandc13.data.network.models.auth.verify


import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    @SerializedName("message")
    val message: String? = null
)