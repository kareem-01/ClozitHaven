package com.example.usecase.repositoryInterfaces

import com.example.entity.Authentication.LogInBody
import com.example.entity.Authentication.SignUpBody

interface AuthenticationRepository {
    suspend fun signUp(body: SignUpBody): String
    suspend fun logIn(body: LogInBody): String
}