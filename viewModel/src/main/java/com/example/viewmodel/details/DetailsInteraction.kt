package com.example.viewmodel.details

interface DetailsInteraction {
    fun onFavoriteClick(itemId: String, isFavorite: Boolean)
    fun addToCart(itemId: String)
    fun onIncrementClick()
    fun onDecrementClick()
    fun onAddToCartClick()
}