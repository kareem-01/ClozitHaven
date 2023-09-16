package com.example.ui

sealed class Screen(val route:String){
    object Home:Screen("HomeScreen")
    object SignUp:Screen("SignUp")
    object LogIn:Screen("LogIn")
}
