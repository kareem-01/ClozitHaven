package com.example.usecase.UseCases.Authentiaction

import com.example.entity.Authentication.LogInBody
import com.example.usecase.repositoryInterfaces.AuthenticationRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {
    suspend operator fun invoke(logInBody: LogInBody): String =
        authenticationRepository.logIn(logInBody)
}