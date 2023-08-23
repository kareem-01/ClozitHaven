package com.example.repository.dataSources

import com.example.repository.models.dto.brands.CategoriesDto
import com.example.repository.models.dto.brands.SubCategoriesDto

interface RemoteDataSource {
    // category
    suspend fun getAllCategories(): CategoriesDto
    suspend fun getAllSubCategories(categoryId: Int): SubCategoriesDto
    // category

    //
}