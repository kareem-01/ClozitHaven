package com.example.ui.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.Screen
import com.example.ui.screens.details.detailsRoute
import com.example.ui.screens.home.homeRoute
import com.example.ui.screens.logIn.logIn
import com.example.ui.screens.search.search
import com.example.ui.screens.signUp.SignUp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        val scope = this
        NavHost(navController, startDestination = Screen.Home.route) {
            homeRoute(scope)
            SignUp()
            logIn()
            search(scope)
            detailsRoute(scope)
        }

    }
}
