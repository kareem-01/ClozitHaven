package com.example.repository.models.dto.products

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("data")
    val `data`: ProductsDto.Data
)
