package com.example.viewmodel.details

sealed interface DetailsUiEffect {
    data object NavigateToLogIn : DetailsUiEffect
}