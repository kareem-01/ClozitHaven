package com.example.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.Screen
import com.example.ui.screens.home.homeRoute
import com.example.ui.screens.signUp.SignUp

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.SignUp.route) {
        homeRoute()
        SignUp()
    }
}
