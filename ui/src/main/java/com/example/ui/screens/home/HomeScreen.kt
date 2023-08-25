package com.example.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    HomeContent()
}

@Composable
fun HomeContent() {

}
