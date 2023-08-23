package com.example.repository.models.dto.brands


import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("data")
    val `data`: List<CategoryDto?>?,
    @SerializedName("metadata")
    val metadata: Metadata?,
    @SerializedName("results")
    val results: Int?
) {
    data class CategoryDto(
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("slug")
        val slug: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?
    )

    data class Metadata(
        @SerializedName("currentPage")
        val currentPage: Int?,
        @SerializedName("limit")
        val limit: Int?,
        @SerializedName("numberOfPages")
        val numberOfPages: Int?
    )
}