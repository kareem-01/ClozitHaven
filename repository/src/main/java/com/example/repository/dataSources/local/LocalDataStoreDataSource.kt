package com.example.repository.dataSources.local

import kotlinx.coroutines.flow.Flow

interface LocalDataStoreDataSource {
    suspend fun setApiKey(apiKey: String)
    fun getApiKey(): Flow<String>
    suspend fun setUserName(name: String)
    suspend fun setEmail(email: String)
    suspend fun getUserName(): Flow<String>
    suspend fun getEmail(): Flow<String>
}