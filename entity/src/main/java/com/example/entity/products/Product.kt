package com.example.entity.products

data class Product(
    val id: String,
    val productName: String,
    val rating: Double,
    val price: Int,
    val image: String,
    val priceAfterDiscount: Int,
    val description: String,
    val piecesLeft: Int,
    val isFavorite: Boolean,
)
