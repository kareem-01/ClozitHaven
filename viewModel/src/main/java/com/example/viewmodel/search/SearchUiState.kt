package com.example.viewmodel.search

import com.example.viewmodel.home.ItemCard
import com.example.viewmodel.utils.Empty

data class SearchUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val recentlySearched: List<ItemCard> = emptyList(),
    val searchResults: List<ItemCard> = emptyList(),
    val searchQuery: String = String.Empty,
    val filters: List<SearchFilter> = emptyList(),
    val allItems: List<ItemCard> = emptyList(),
)

data class SearchFilter(
    val name: String = "All",
    val isSelected: Boolean = false

)