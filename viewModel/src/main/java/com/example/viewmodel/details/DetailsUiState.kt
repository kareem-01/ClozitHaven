package com.example.viewmodel.details

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
    val size: Int = 0,
    val rating : Int = 0,
    val id:String = "",
)
