package com.example.ui.screens.Search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen
import com.example.ui.screens.signUp.SignUpScreen

fun NavGraphBuilder.search() {
    composable(Screen.SignUp.route) {
        SearchScreen()
    }
}
