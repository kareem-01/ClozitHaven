package com.example.repository.respository

import com.example.entity.Authentication.SignUpBody
import com.example.repository.dataSources.remote.RemoteDataSource
import com.example.usecase.repositoryInterfaces.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) :
    AuthenticationRepository {
    override suspend fun signUp(body: SignUpBody): String {
        return dataSource.signUp(body).token.toString()
    }
}