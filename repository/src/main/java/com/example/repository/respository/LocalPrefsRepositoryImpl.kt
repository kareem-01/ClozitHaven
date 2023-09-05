package com.example.repository.respository

import com.example.repository.dataSources.local.LocalDataStoreDataSource
import com.example.usecase.repositoryInterfaces.LocalPrefsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalPrefsRepositoryImpl @Inject constructor(private val dataStore: LocalDataStoreDataSource) :
    LocalPrefsRepository {
    override suspend fun setUserApiKey(key: String) = dataStore.setApiKey(key)

    override suspend fun getUserApiKey(): Flow<String> = dataStore.getApiKey()

    override suspend fun setUserUserName(name: String) = dataStore.setUserName(name)

    override suspend fun getUserUserName(): Flow<String> = dataStore.getUserName()
}