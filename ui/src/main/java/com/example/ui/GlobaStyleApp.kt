package com.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ui.nav.RootNavGraph

val LocalNavController = compositionLocalOf<NavHostController> { error("NO NavController") }

@Composable
fun GlobaStyleApp() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        RootNavGraph(navController)
    }

}