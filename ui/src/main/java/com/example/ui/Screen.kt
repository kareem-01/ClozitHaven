package com.example.ui

const val ITEM_ID = "itemId"

sealed class Screen(val route: String) {
    data object Home : Screen("HomeScreen")
    data object SignUp : Screen("SignUp")
    data object LogIn : Screen("LogIn")
    data object Search : Screen("SearchScreen")
    data object Cart : Screen("CartScreen")
    data object Favorite : Screen("FavoriteScreen")
    data object Profile : Screen("ProfileScreen")
    data object Details : Screen(route = "DetailsScreen/{$ITEM_ID}") {
        fun passProductId(productId: String) = "DetailsScreen/$productId"
    }
}
