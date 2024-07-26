package com.example.usecase.repositoryInterfaces

import com.example.entity.products.Product

interface ProductsRepository {
    suspend fun getProducts(category: String? = null): List<Product>
    suspend fun addProductToWishList(itemId: String): String
    suspend fun deleteProductFromWishList(itemId: String)
}