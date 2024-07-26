package com.example.ui.screens.logIn

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen

fun NavGraphBuilder.logIn() {
    composable(Screen.LogIn.route) {
        LogInScreen()
    }
}

fun NavController.navigateToLogIn(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.LogIn.route,builder)
}