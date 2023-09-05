package com.example.remote.dataSource

import com.example.entity.Authentication.SignUpBody
import com.example.remote.service.Authentication.AuthenticationService
import com.example.remote.service.categories.CategoriesService
import com.example.repository.dataSources.remote.RemoteDataSource
import com.example.repository.models.dto.Authentication.SignUpDto
import com.example.repository.models.dto.brands.CategoriesDto
import com.example.repository.models.dto.brands.SubCategoriesDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val categoriesService: CategoriesService,
    private val authenticationService: AuthenticationService,
) : RemoteDataSource, BaseRemoteDataSource() {
    override suspend fun signUp(body: SignUpBody): SignUpDto {
        return wrapApiCall { authenticationService.signUp(body) }
    }

    override suspend fun getAllCategories(): CategoriesDto {
        return wrapApiCall { categoriesService.getAllCategories() }
    }

    override suspend fun getAllSubCategories(categoryId: Int): SubCategoriesDto {
        return wrapApiCall { categoriesService.getAllSubCategories() }
    }


}