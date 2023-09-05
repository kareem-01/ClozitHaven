package com.example.usecase.repositoryInterfaces

import kotlinx.coroutines.flow.Flow

interface LocalPrefsRepository {
    suspend fun setUserApiKey(key: String)
    suspend fun getUserApiKey(): Flow<String>
    suspend fun setUserUserName(name: String)
    suspend fun getUserUserName(): Flow<String>
}