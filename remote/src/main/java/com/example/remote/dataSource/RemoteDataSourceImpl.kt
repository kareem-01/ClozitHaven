package com.example.remote.dataSource

import com.example.remote.service.categories.CategoriesService
import com.example.repository.dataSources.RemoteDataSource
import com.example.repository.models.dto.brands.CategoriesDto
import com.example.repository.models.dto.brands.SubCategoriesDto
import com.example.repository.utils.NoInternetException
import com.example.repository.utils.NullDataException
import com.example.repository.utils.ServerException
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val categoriesService: CategoriesService,
) : RemoteDataSource {
    override suspend fun getAllCategories(): CategoriesDto {
        return wrapApiCall { categoriesService.getAllCategories() }
    }

    override suspend fun getAllSubCategories(categoryId: Int): SubCategoriesDto {
        return wrapApiCall { categoriesService.getAllSubCategories() }
    }

    private suspend fun <T> wrapApiCall(request: suspend () -> Response<T>): T {
        return try {
            val response = request()
            if (response.isSuccessful)
                response.body() ?: throw NullDataException("empty data returned")
            else {
                throw when (response.code()) {

                    else -> throw ServerException(response.message())
                }
            }
        } catch (e: UnknownHostException) {
            throw NoInternetException(e.message)
        }
    }
}