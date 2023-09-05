package com.example.repository.respository

import com.example.entity.categories.Category
import com.example.repository.dataSources.remote.RemoteDataSource
import com.example.repository.mappers.toCategory
import com.example.usecase.repositoryInterfaces.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : CategoriesRepository {
    override suspend fun getAllCategories(): List<Category> {
        return dataSource.getAllCategories().toCategory()
    }
}