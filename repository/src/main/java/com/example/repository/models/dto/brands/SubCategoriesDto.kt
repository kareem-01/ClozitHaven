package com.example.repository.models.dto.brands


import com.google.gson.annotations.SerializedName

data class SubCategoriesDto(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("metadata")
    val metadata: Metadata?,
    @SerializedName("results")
    val results: Int?
) {
    data class Data(
        @SerializedName("category")
        val category: String?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("_id")
        val id: String?,
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
        @SerializedName("nextPage")
        val nextPage: Int?,
        @SerializedName("numberOfPages")
        val numberOfPages: Int?,
        @SerializedName("prevPage")
        val previousPage: Int?

    )
}