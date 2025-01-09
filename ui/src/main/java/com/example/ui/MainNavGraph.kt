package com.example.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.nav.homeRoute
import com.example.ui.nav.search

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavGraph(
    scope: SharedTransitionScope,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Screen.Main.route,
        startDestination = Screen.Home.route
    ) {
        homeRoute(scope)
        search(scope)
    }

}