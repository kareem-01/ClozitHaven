package com.example.entity.Authentication

data class SignUpBody(
    val name: String,
    val email: String,
    val password: String,
    val rePassword: String,
    val phone: String,
)
