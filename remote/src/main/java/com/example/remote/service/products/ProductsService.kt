package com.example.remote.service.products

import com.example.repository.models.dto.products.ProductDto
import com.example.repository.models.dto.products.ProductId
import com.example.repository.models.dto.products.ProductsDto
import com.example.repository.models.dto.products.WishListConfirmDto
import com.example.repository.models.dto.products.WishListDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {
    @GET("products")
    suspend fun getAllProducts(@Query("category[in]") category: String? = null): Response<ProductsDto>

    @GET("products/{productId}")
    suspend fun getProductById(@Path("productId") productId: String): Response<ProductDto>

    @GET("wishlist")
    suspend fun getWishListItems(): Response<WishListDto>

    @POST("wishlist")
    suspend fun addToWishList(@Body productId: ProductId): Response<WishListConfirmDto>

    @DELETE("wishlist/{itemId}")
    suspend fun deleteProductFromWishList(@Path("itemId") itemId: String)

}