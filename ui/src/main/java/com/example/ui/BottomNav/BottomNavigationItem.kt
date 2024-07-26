package com.example.ui.BottomNav

import com.example.ui.R
import com.example.ui.Screen

sealed class BottomNavigationItem(val icon:Int, val screenRoute:String){
    data object Home:BottomNavigationItem( icon = R.drawable.home_icon  , screenRoute = Screen.Home.route)
    data object Search:BottomNavigationItem( icon =R.drawable.search_icon  , screenRoute = Screen.Search.route)
    data object Cart:BottomNavigationItem( icon =R.drawable.email_icon  , screenRoute = Screen.Cart.route)
    data object Favorite:BottomNavigationItem( icon =R.drawable.heart_icon  , screenRoute = Screen.Favorite.route)
    data object Profile:BottomNavigationItem( icon =R.drawable.profile_icon  , screenRoute = Screen.Profile.route)
}
