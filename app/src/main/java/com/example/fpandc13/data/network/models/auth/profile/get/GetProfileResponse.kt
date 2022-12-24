package com.example.fpandc13.data.network.models.auth.profile.get


import com.google.gson.annotations.SerializedName

data class GetProfileResponse(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("status")
    val status: String? = null
)