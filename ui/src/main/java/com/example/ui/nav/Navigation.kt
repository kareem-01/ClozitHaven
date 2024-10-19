@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.ui.nav

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.example.ui.Screen
import com.example.ui.screens.details.DetailsScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.logIn.LogInScreen
import com.example.ui.screens.search.SearchScreen
import com.example.ui.screens.signUp.SignUpScreen


fun NavGraphBuilder.detailsRoute(sharedTransitionScope: SharedTransitionScope) {
    composable(Screen.Details.route) {
        val productId = Screen.Details.args?.getString("id")!!
        val image = Screen.Details.args?.getString("image")!!
        val name = Screen.Details.args?.getString("name")!!
        sharedTransitionScope.DetailsScreen(productId, image, name, this@composable)
    }
}

fun NavController.navigateToDetails(
    productId: String,
    imageUrl: String,
    name: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    Screen.Details.args = bundleOf("id" to productId, "image" to imageUrl, "name" to name)
    navigate(Screen.Details.route, builder)
}

fun NavGraphBuilder.signUp() {
    composable(Screen.SignUp.route) {
        SignUpScreen()
    }
}

fun NavController.navigateToSignUp(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.SignUp.route, builder)
}

fun NavGraphBuilder.search(sharedTransitionScope: SharedTransitionScope) {
    composable(Screen.Search.route) {
        sharedTransitionScope.SearchScreen(this@composable)
    }
}

fun NavGraphBuilder.logIn() {
    composable(Screen.LogIn.route) {
        LogInScreen()
    }
}

fun NavController.navigateToLogIn(builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(Screen.LogIn.route, builder)
}

fun NavGraphBuilder.homeRoute(
    sharedTransitionScope: SharedTransitionScope,
) {

    composable(
        route = Screen.Home.route,
    ) { entry ->
        sharedTransitionScope.HomeScreen(this@composable)
    }
}
