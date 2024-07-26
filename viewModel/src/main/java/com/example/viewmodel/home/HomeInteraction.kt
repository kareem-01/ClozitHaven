package com.example.viewmodel.home

interface HomeInteraction {
    fun onItemClick(itemId: String)
    fun onTabClick(categoryId: String?)
    fun onFavoriteClick(itemId: String,isFavorite:Boolean)
}