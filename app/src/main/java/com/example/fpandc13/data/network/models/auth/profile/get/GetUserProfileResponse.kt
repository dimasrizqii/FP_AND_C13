package com.example.fpandc13.data.network.models.auth.profile.get


import com.google.gson.annotations.SerializedName

data class GetUserProfileResponse(
    @SerializedName("Profile")
    val `profile`: Profile?
)