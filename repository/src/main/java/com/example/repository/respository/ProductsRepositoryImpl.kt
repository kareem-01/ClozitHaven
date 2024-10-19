package com.example.repository.respository

import com.example.entity.products.Product
import com.example.repository.dataSources.remote.RemoteDataSource
import com.example.repository.mappers.toEntity
import com.example.usecase.repositoryInterfaces.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ProductsRepository {
    override suspend fun getProducts(category: String?): List<Product> {
//        remoteDataSource.getWishList().also { wishList ->
//            return remoteDataSource.getProducts(category)
//                .toEntity(wishList.data?.map { it?.id!! } ?: emptyList())
//        }

        val wishListResult = runCatching { remoteDataSource.getWishList() }
        val wishList = wishListResult.getOrNull()?.data?.map { it?.id!! } ?: emptyList()
        return remoteDataSource.getProducts(category).toEntity(wishList)
    }

    override suspend fun getProductById(productId: String): Product {
        val wishListResult = runCatching { remoteDataSource.getWishList() }
        val wishList = wishListResult.getOrNull()?.data?.map { it?.id!! } ?: emptyList()
        return remoteDataSource.getProductById(productId).toEntity(wishList)
    }

    override suspend fun addProductToWishList(itemId: String): String {
        return remoteDataSource.addProductToWishList(itemId).data?.last()!!
    }

    override suspend fun deleteProductFromWishList(itemId: String) {
        TODO("Not yet implemented")
    }

}