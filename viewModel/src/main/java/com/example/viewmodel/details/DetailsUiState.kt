package com.example.viewmodel.details

import com.example.entity.products.Product

data class DetailsUiState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val isFavorite: Boolean = false,
    val imageUrl: String = "",
    val title: String = "",
    val quantityToOrder: Int = 1,
    val price: Int = 0,
    val description: String = "",
    val selectedColor: Int = 0,
    val selectedSize: Int = 0,
    val size: Int = 0,
    val rating: String = "",
    val id: String = "",
    val remainingQuantity: Int = 0,
    val isFashion: Boolean = true,
    val message: String? = null,
)

fun Product.toUiState() = DetailsUiState(
    imageUrl = image,
    title = productName,
    price = price,
    description = description,
    rating = rating.toString(),
    id = id,
    isFavorite = isFavorite,
    remainingQuantity = piecesLeft,
    isFashion = category.contains("Fashion")
)