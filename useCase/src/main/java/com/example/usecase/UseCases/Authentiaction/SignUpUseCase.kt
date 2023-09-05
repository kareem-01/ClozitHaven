package com.example.usecase.UseCases.Authentiaction

import com.example.entity.Authentication.SignUpBody
import com.example.usecase.repositoryInterfaces.AuthenticationRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(body: SignUpBody): String = authenticationRepository.signUp(body)
}