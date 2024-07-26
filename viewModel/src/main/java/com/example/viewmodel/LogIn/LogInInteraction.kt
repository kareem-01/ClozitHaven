package com.example.viewmodel.LogIn

import com.example.viewmodel.signUp.TextType

interface LogInInteraction {
    fun updateUiState(type: TextType, text: String)
    fun logIn()
    fun updateAllStates(email: String, password: String)
    fun clearMessage()
    fun onLogInClick()
}