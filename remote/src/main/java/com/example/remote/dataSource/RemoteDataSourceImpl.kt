package com.example.remote.dataSource

import com.example.entity.Authentication.LogInBody
import com.example.entity.Authentication.SignUpBody
import com.example.remote.service.Authentication.AuthenticationService
import com.example.remote.service.categories.CategoriesService
import com.example.remote.service.products.ProductsService
import com.example.repository.dataSources.remote.RemoteDataSource
import com.example.repository.models.dto.Authentication.LogInDto
import com.example.repository.models.dto.Authentication.SignUpDto
import com.example.repository.models.dto.brands.CategoriesDto
import com.example.repository.models.dto.brands.SubCategoriesDto
import com.example.repository.models.dto.products.ProductId
import com.example.repository.models.dto.products.ProductsDto
import com.example.repository.models.dto.products.WishListDto
import com.example.repository.models.dto.products.WishListConfirmDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val categoriesService: CategoriesService,
    private val authenticationService: AuthenticationService,
    private val productsService: ProductsService,
) : RemoteDataSource, BaseRemoteDataSource() {
    override suspend fun signUp(body: SignUpBody): SignUpDto {
        return wrapApiCall { authenticationService.signUp(body) }
    }

    override suspend fun logIn(body: LogInBody): LogInDto {
        return wrapApiCall { authenticationService.logIn(body) }
    }

    override suspend fun getAllCategories(): CategoriesDto {
        return wrapApiCall { categoriesService.getAllCategories() }
    }

    override suspend fun getAllSubCategories(categoryId: Int): SubCategoriesDto {
        return wrapApiCall { categoriesService.getAllSubCategories() }
    }

    override suspend fun getProducts(category: String?): ProductsDto {
        return wrapApiCall { productsService.getAllProducts(category) }
    }

    override suspend fun getWishList(): WishListDto {
        return wrapApiCall { productsService.getWishListItems() }
    }

    override suspend fun addProductToWishList(itemId: String): WishListConfirmDto {
        return wrapApiCall { productsService.addToWishList(ProductId(itemId)) }
    }

    override suspend fun deleteProductFromWishList(itemId: String) {
        TODO("Not yet implemented")
    }


}