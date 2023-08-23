package com.example.repository.mappers

import com.example.entity.categories.Category
import com.example.repository.models.dto.brands.CategoriesDto

fun CategoriesDto.toCategory(): List<Category> {
    return data?.map { it!!.toCategory() } ?: emptyList()
}

fun CategoriesDto.CategoryDto.toCategory(): Category = Category(
    id ?: "",
    name ?: "",
    image ?: "",
)
