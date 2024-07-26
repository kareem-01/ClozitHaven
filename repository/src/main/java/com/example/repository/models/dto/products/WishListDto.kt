package com.example.repository.models.dto.products


import com.google.gson.annotations.SerializedName

data class WishListDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("status")
    val status: String?
) {
    data class Data(
        @SerializedName("brand")
        val brand: Brand?,
        @SerializedName("category")
        val category: Category?,
        @SerializedName("createdAt")
        val createdAt: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("_id")
        val itemId: String?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("imageCover")
        val imageCover: String?,
        @SerializedName("images")
        val images: List<String?>?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("quantity")
        val quantity: Int?,
        @SerializedName("ratingsAverage")
        val ratingsAverage: Double?,
        @SerializedName("ratingsQuantity")
        val ratingsQuantity: Int?,
        @SerializedName("slug")
        val slug: String?,
        @SerializedName("sold")
        val sold: Int?,
        @SerializedName("subcategory")
        val subcategory: List<Subcategory?>?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("updatedAt")
        val updatedAt: String?,
        @SerializedName("__v")
        val v: Int?
    ) {
        data class Brand(
            @SerializedName("_id")
            val id: String?,
            @SerializedName("image")
            val image: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?
        )

        data class Category(
            @SerializedName("_id")
            val id: String?,
            @SerializedName("image")
            val image: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?
        )

        data class Subcategory(
            @SerializedName("category")
            val category: String?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?
        )
    }
}