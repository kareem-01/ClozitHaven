package com.example.repository.models.dto.Authentication


import com.google.gson.annotations.SerializedName

data class LogInDto(
    @SerializedName("statusMsg")
    val statusMessage:String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?,
    @SerializedName("user")
    val user: User?
) {
    data class User(
        @SerializedName("email")
        val email: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("role")
        val role: String?
    )
}