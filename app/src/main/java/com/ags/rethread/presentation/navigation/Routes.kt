package com.ags.rethread.presentation.navigation

sealed class Routes(val route: String) {

    object Splash : Routes("splash_screen")
    object Auth : Routes("auth_screen")
    object Login : Routes("login_screen")
    object Register : Routes("register_screen")
}