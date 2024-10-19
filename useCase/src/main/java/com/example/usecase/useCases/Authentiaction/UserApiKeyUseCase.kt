package com.example.usecase.useCases.Authentiaction

import com.example.usecase.repositoryInterfaces.LocalPrefsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserApiKeyUseCase @Inject constructor(private val localPrefsRepository: LocalPrefsRepository) {

    suspend fun setUserApiKey(key:String) = localPrefsRepository.setUserApiKey(key)
    suspend fun getUserApiKey() : Flow<String> = localPrefsRepository.getUserApiKey()
}