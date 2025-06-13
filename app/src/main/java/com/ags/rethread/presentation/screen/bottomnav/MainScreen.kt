package com.ags.rethread.presentation.screen.bottomnav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ags.rethread.presentation.navigation.Routes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ags.rethread.presentation.screen.addthread.AddThreadScreen
import com.ags.rethread.presentation.screen.home.HomeScreen
import com.ags.rethread.presentation.screen.profile.ProfileScreen
import com.ags.rethread.presentation.screen.search.SearchScreen

@Composable
fun MainScreen() {

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            MyBottomNavBar(bottomNavController = bottomNavController)
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.Home.route) { HomeScreen() }
            composable(Routes.Search.route) { SearchScreen() }
            composable(Routes.AddThread.route) { AddThreadScreen() }
            composable(Routes.Profile.route) { ProfileScreen() }
        }
    }
}