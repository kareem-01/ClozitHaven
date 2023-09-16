package com.example.viewmodel.signUp

interface SignUpInteraction {
    fun updateUiState(type: TextType, text: String)
    fun signUp()
    fun updateAllStates(
        userName: String,
        email: String,
        password: String,
        rePassword: String,
        phoneNumber: String
    )
    fun onLogInClick()
}
