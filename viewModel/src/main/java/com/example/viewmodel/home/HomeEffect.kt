package com.example.viewmodel.home

import com.example.viewmodel.BaseViewModel

sealed interface HomeEffect : BaseViewModel.UiEffect {
    data object NavigateToLogIn : HomeEffect
    data class NavigateToDetails(val id: String) : HomeEffect
}