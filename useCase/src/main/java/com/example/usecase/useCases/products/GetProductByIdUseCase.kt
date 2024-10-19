package com.example.usecase.useCases.products

import com.example.usecase.repositoryInterfaces.ProductsRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(private val repository: ProductsRepository) {
    suspend operator fun invoke(productId: String) = repository.getProductById(productId)
}