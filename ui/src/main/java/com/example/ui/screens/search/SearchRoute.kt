@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.ui.screens.search

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ui.Banana.AddPostScreen
import com.example.ui.Screen

fun NavGraphBuilder.search(sharedTransitionScope: SharedTransitionScope) {
    composable(Screen.Search.route) {
        sharedTransitionScope.SearchScreen()
    }
}
