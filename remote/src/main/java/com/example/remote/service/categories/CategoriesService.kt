package com.example.remote.service.categories

import com.example.repository.models.dto.brands.CategoriesDto
import com.example.repository.models.dto.brands.SubCategoriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoriesService {
    @GET("categories")
    suspend fun getAllCategories(): Response<CategoriesDto>

    @GET("categories/{category_id}")
    suspend fun getSubCategoriesByCategory(@Path("category_id") categoryId: Int)

    @GET("subcategories")
    suspend fun getAllSubCategories(@Query("page") page: Int? = null): Response<SubCategoriesDto>
}