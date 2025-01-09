@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.ui.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ui.Screen

val LocalSharedTransitionScope =
    compositionLocalOf<SharedTransitionScope> { error(" no shared transition scope passed") }

@Composable
fun RootNavGraph(navController: NavHostController) {
    SharedTransitionLayout {
        val scope = this

        CompositionLocalProvider(LocalSharedTransitionScope provides scope) {
            NavHost(
                navController,
                route = "root_host",
                startDestination = Screen.Main.route
            ) {
                signUp()
                logIn()
                mainNavGraph(scope)
                detailsRoute(scope)
            }
        }

    }
}
