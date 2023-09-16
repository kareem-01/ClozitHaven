package com.example.repository.dataSources.remote

import com.example.entity.Authentication.LogInBody
import com.example.entity.Authentication.SignUpBody
import com.example.repository.models.dto.Authentication.LogInDto
import com.example.repository.models.dto.Authentication.SignUpDto
import com.example.repository.models.dto.brands.CategoriesDto
import com.example.repository.models.dto.brands.SubCategoriesDto

interface RemoteDataSource {

    //authentication
    suspend fun signUp(body: SignUpBody):SignUpDto
    suspend fun logIn(body:LogInBody):LogInDto

    //authentication
    // category
    suspend fun getAllCategories(): CategoriesDto
    suspend fun getAllSubCategories(categoryId: Int): SubCategoriesDto
    // category

    //
}