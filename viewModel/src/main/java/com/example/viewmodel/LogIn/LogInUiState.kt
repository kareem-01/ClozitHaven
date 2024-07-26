package com.example.viewmodel.LogIn

import com.example.entity.Authentication.LogInBody

data class LogInUiState(
    val email: String = "",
    val password: String = "",
    val message: String? = null,
)

fun LogInUiState.toBody(): LogInBody {
    return LogInBody(
        email = email,
        password = password,
    )
}