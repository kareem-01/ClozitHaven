package com.example.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.theme.Space8

@Composable
fun HomeShimmer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit
) {
    if (isLoading) {
        Column(verticalArrangement = Arrangement.spacedBy(Space8)) {
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .shimmerEffect(isLoading = isLoading)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(20.dp)
                    .shimmerEffect(isLoading = isLoading)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(20.dp)
                    .shimmerEffect(isLoading)
            )
        }
    } else {
        contentAfterLoading()
    }
}