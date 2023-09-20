package com.example.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.Screen
import com.example.ui.screens.Search.search
import com.example.ui.screens.home.homeRoute
import com.example.ui.screens.logIn.logIn
import com.example.ui.screens.signUp.SignUp

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        homeRoute()
        SignUp()
        logIn()
        search()
    }
}
