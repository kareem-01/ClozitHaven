package com.example.usecase.useCases.products

import com.example.usecase.repositoryInterfaces.ProductsRepository
import javax.inject.Inject

class AddProductToFavoriteUseCase @Inject constructor(private val repository: ProductsRepository) {
    suspend operator fun invoke(itemId: String) = repository.addProductToWishList(itemId)
}