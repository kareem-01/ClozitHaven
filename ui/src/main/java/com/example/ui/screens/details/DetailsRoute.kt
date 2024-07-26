package com.example.ui.screens.details

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.detailsRoute(sharedTransitionScope: SharedTransitionScope) {
    composable(Screen.Details.route) {
        sharedTransitionScope.DetailsScreen()
    }
}