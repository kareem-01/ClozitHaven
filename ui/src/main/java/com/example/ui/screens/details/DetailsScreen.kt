package com.example.ui.screens.details

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    productId: String
) {

    DetailsContent()
}

@Composable
fun DetailsContent() {
    Box(modifier = Modifier.fillMaxSize()){
        AsyncImage(model = , contentDescription = )
    }

}
