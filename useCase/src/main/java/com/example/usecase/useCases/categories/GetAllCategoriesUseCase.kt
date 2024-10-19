package com.example.usecase.useCases.categories

import com.example.entity.categories.Category
import com.example.usecase.repositoryInterfaces.CategoriesRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(): List<Category> = categoriesRepository.getAllCategories()
}