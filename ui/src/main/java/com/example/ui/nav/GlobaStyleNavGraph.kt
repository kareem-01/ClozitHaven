package com.example.ui.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        val scope = this
        NavHost(navController, startDestination = Screen.Home.route) {
            homeRoute(scope)
            signUp()
            logIn()
            search(scope)
            detailsRoute(scope)
        }

    }
}
