package com.example.ui

sealed class Screen(val route:String){
    data object Home:Screen("HomeScreen")
    data object SignUp:Screen("SignUp")
    data object LogIn:Screen("LogIn")
    data object Search:Screen("SearchScreen")
    data object Cart:Screen("CartScreen")
    data object Favorite:Screen("FavoriteScreen")
    data object Profile:Screen("ProfileScreen")
}
