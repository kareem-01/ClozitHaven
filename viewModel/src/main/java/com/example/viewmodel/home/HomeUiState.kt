package com.example.viewmodel.home

import com.example.entity.categories.Category
import com.example.entity.products.Product
import com.example.viewmodel.utils.Empty

data class HomeUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val items: List<ItemCard> = emptyList(),
    val categories: List<CategoryUi> = listOf(CategoryUi(String.Empty, String.Empty)),
    val selectedCategoryId: String = String.Empty,
    val isSignedIn: Boolean = false,
    )

data class ItemCard(
    val isFavorite: Boolean,
    val itemId: String,
    val itemName: String,
    val currentPrice: String,
    val rating: String,
    val image: String,
)

data class CategoryUi(
    val name: String,
    val id: String,
)

fun Category.toCategory(): CategoryUi {
    return CategoryUi(name = name!!, id = id!!)
}

fun List<Category>.toCategory(): List<CategoryUi> =
    this.map { it.toCategory() }


fun Product.toHomeCard(): ItemCard =
    ItemCard(
        isFavorite = isFavorite,
        itemId = id,
        itemName = productName,
        currentPrice = price.toString(),
        rating = rating.toString(),
        image = image,
    )

fun List<Product>.toItemCard(): List<ItemCard> =
    this.map { it.toHomeCard() }
