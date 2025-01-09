package com.example.viewmodel.signUp

import com.example.viewmodel.BaseViewModel

sealed interface SignUpEffect : BaseViewModel.UiEffect {
    data object NavigateBack : SignUpEffect
    data object NavigateToLogIn : SignUpEffect
    data object NavigateToHome : SignUpEffect
}
