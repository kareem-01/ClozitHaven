package com.example.remote.service.Authentication

import com.example.entity.Authentication.LogInBody
import com.example.entity.Authentication.SignUpBody
import com.example.repository.models.dto.Authentication.LogInDto
import com.example.repository.models.dto.Authentication.SignUpDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("auth/signup")
    suspend fun signUp(@Body body: SignUpBody): Response<SignUpDto>

    @POST("auth/signin")
    suspend fun logIn(@Body body: LogInBody): Response<LogInDto>
}