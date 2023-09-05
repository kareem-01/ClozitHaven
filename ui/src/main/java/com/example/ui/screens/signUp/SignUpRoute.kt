package com.example.ui.screens.signUp

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen

fun NavGraphBuilder.SignUp() {
    composable(Screen.SignUp.route) {
        SignUpScreen()
    }
}