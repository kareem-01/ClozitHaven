package com.example.usecase.repositoryInterfaces

import com.example.entity.categories.Category

interface CategoriesRepository {
    suspend fun getAllCategories(): List<Category>
}