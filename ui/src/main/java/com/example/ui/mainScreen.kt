package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.theme.CustomColors

@Composable
fun MainScreen(nestedNavGraph: @Composable () -> Unit, bottomBar: @Composable () -> Unit) {
    Scaffold(bottomBar = bottomBar) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.CustomColors().background)
        ) {
            nestedNavGraph.invoke()
        }
    }
}