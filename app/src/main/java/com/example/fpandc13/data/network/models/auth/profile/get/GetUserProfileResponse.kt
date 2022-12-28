package com.example.fpandc13.data.network.models.auth.profile.get


import com.google.gson.annotations.SerializedName

data class GetUserProfileResponse(
    @SerializedName("profile")
    val `profile`: profile?
//    @SerializedName("message")
//    val message: String?,
//    @SerializedName("status")
//    val status: String?
)