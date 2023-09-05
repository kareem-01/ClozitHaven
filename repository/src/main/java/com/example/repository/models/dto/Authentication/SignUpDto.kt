package com.example.repository.models.dto.Authentication


import com.google.gson.annotations.SerializedName

data class SignUpDto(
    @SerializedName("statusMsg")
    val statusMsg: String?,
    @SerializedName("errors")
    val errors: Errors?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("user")
    val user: User?,
    @SerializedName("token")
    val token: String?,
) {
    data class Errors(
        @SerializedName("location")
        val location: String?,
        @SerializedName("msg")
        val msg: String?,
        @SerializedName("param")
        val `param`: String?,
        @SerializedName("value")
        val value: String?
    )

    data class User(
        @SerializedName("name")
        val name: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("role")
        val role: String?
    )
}