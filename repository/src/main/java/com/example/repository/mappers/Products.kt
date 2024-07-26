package com.example.repository.mappers

import com.example.entity.products.Product
import com.example.repository.models.dto.products.ProductsDto
import com.example.repository.utils.Empty
import com.example.repository.utils.emptyFromBackEnd

fun ProductsDto.toEntity(favorites: List<String>): List<Product> =
    this.data?.map { it?.toEntity(favorites)!! } ?: emptyList()

fun ProductsDto.Data.toEntity(favorites: List<String>): Product {
    return Product(
        id = id!!,
        productName = title ?: emptyFromBackEnd,
        rating = ratingsAverage ?: Double.Empty(),
        price = price ?: Int.Empty(),
        image = imageCover ?: String.Empty(),
        priceAfterDiscount = priceAfterDiscount ?: Int.Empty(),
        description = description ?: String.Empty(),
        piecesLeft = quantity ?: Int.Empty(),
        isFavorite = id in favorites,
    )
}