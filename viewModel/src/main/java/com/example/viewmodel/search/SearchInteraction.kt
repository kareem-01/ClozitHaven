package com.example.viewmodel.search

interface SearchInteraction {
    fun search(query: String)
    fun onItemClick(id: String)
    fun onQueryChanged(query: String)
    fun onFilterClick()
    fun onFavoriteClick(itemId: String, isFavorite: Boolean)
    fun clearSearchQuery()
    fun clearAllRecentSearches()
}