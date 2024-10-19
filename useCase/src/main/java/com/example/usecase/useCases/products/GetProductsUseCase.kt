package com.example.usecase.useCases.products

import com.example.entity.products.Product
import com.example.usecase.repositoryInterfaces.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(categories: String? = null):List<Product> =
        productsRepository.getProducts(categories)
}