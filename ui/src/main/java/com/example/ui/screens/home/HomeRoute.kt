@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.ui.screens.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen

fun NavGraphBuilder.homeRoute(sharedTransitionScope: SharedTransitionScope) {
    composable(Screen.Home.route) {
        sharedTransitionScope.HomeScreen()
    }
}