package com.ags.rethread.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ags.rethread.presentation.screen.auth.AuthScreen
import com.ags.rethread.presentation.screen.auth.LoginScreen
import com.ags.rethread.presentation.screen.auth.RegisterScreen
import com.ags.rethread.presentation.screen.bottomnav.MainScreen
import com.ags.rethread.presentation.screen.splash.SplashScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {

        composable(route = Routes.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Routes.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(route = Routes.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Routes.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Routes.Main.route) {
            MainScreen(navController = navController)
        }
    }
}