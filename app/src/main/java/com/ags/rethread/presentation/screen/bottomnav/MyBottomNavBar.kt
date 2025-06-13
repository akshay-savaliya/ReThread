package com.ags.rethread.presentation.screen.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ags.rethread.presentation.navigation.Routes

@Composable
fun MyBottomNavBar(bottomNavController: NavHostController) {

    val bottomNavItemList = listOf<BottomNavItem>(
        BottomNavItem(label = "Home", icon = Icons.Default.Home, route = Routes.Home.route),
        BottomNavItem(label = "Search", icon = Icons.Default.Search, route = Routes.Search.route),
        BottomNavItem(label = "Add Thread", icon = Icons.Default.Add, route = Routes.AddThread.route),
        BottomNavItem(label = "Profile", icon = Icons.Default.Person, route = Routes.Profile.route)
    )

    val currentBackStackEntry = bottomNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    BottomAppBar {
        bottomNavItemList.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        bottomNavController.navigate(item.route) {
                            popUpTo(bottomNavController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                alwaysShowLabel = false
            )
        }
    }
}