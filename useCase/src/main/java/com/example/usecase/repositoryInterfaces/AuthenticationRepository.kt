package com.example.usecase.repositoryInterfaces

import com.example.entity.Authentication.SignUpBody

interface AuthenticationRepository {
    suspend fun signUp(body:SignUpBody):String
}