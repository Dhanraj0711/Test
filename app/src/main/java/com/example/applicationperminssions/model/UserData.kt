package com.example.applicationperminssions.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("result_code")
    val result_code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("username")
    val username: String
)