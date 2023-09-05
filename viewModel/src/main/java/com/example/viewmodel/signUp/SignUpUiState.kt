package com.example.viewmodel.signUp

import com.example.entity.Authentication.SignUpBody

data class SignUpUiState(
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val phoneNumber: String = "",
    val message: String? = null,
)


fun SignUpUiState.toSignUpBody():SignUpBody{
    return SignUpBody(
        name = this.userName,
        email = email,
        password = password,
        rePassword = confirmPassword,
        phone = phoneNumber,
    )
}