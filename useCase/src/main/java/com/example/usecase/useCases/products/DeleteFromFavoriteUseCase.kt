package com.example.usecase.useCases.products

import com.example.usecase.repositoryInterfaces.ProductsRepository
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(private val productsRepository: ProductsRepository) {
    suspend operator fun invoke(itemId: String) {}
}