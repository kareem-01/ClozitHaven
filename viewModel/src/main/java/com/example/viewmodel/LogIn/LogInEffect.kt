package com.example.viewmodel.LogIn

import com.example.viewmodel.BaseViewModel

sealed interface LogInEffect : BaseViewModel.UiEffect {
    data object NavigateBack : LogInEffect
    data object NavigateToLogIn : LogInEffect
}