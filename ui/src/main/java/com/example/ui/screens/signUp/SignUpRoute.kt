package com.example.ui.screens.signUp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen

fun NavGraphBuilder.SignUp() {
    composable(Screen.SignUp.route) {
        SignUpScreen()
    }
}

fun NavController.navigateToSignUp(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.SignUp.route,builder)
}