package com.example.repository.models.dto.products


import com.google.gson.annotations.SerializedName

data class WishListConfirmDto(
    @SerializedName("data")
    val `data`: List<String?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)