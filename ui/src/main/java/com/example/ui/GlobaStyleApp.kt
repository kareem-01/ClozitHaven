package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ui.BottomNav.BottomNavigation
import com.example.ui.BottomNav.currentRoute
import com.example.ui.nav.NavGraph

val LocalNavController = compositionLocalOf<NavHostController> { error("NO NavController") }

@Composable
fun GlobaStyleApp() {
    val navController = rememberNavController()
    val screens = listOf(
        Screen.Home.route,
        Screen.Search.route,
        Screen.Favorite.route,
        Screen.Cart.route,
        Screen.Profile.route
    )


    Scaffold(bottomBar = {
        if (currentRoute(navController) in screens)
            BottomNavigation(navController)
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            CompositionLocalProvider(LocalNavController provides navController) {
                NavGraph(navController)
            }
        }
    }


}