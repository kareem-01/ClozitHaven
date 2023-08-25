package com.example.ui.screens.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen

fun NavGraphBuilder.homeRoute() {
    composable(Screen.Home.route) {
        HomeScreen()
    }
}