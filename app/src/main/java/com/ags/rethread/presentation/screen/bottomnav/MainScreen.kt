package com.ags.rethread.presentation.screen.bottomnav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ags.rethread.presentation.navigation.Routes
import com.ags.rethread.presentation.screen.auth.AuthViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                viewModel.logout()
                navController.navigate(Routes.Auth.route) {
                    popUpTo(Routes.Main.route) { inclusive = true }
                }
            }

        ) {
            Text(text = "Logout")
        }
    }
}