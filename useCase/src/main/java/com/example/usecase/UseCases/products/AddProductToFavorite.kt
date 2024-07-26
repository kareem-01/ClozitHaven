package com.example.usecase.UseCases.products

import com.example.usecase.repositoryInterfaces.ProductsRepository
import javax.inject.Inject

class AddProductToFavorite @Inject constructor(private val repository: ProductsRepository) {


    suspend operator fun invoke(itemId: String) = repository.addProductToWishList(itemId)
}