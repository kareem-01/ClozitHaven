package com.example.ui.screens.details

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    productId:String
) {
    DetailsContent()
}

@Composable
fun DetailsContent() {


}
