package com.ags.rethread.presentation.screen.bottomnav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ags.rethread.presentation.navigation.Routes
import com.ags.rethread.presentation.screen.auth.AuthViewModel
import androidx.compose.runtime.getValue

@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val userData by viewModel.userData.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Main Screen")
        Text(text = "Name: ${userData.name}")
        Text(text = "Username: ${userData.username}")
        Text(text = "Bio: ${userData.bio}")
        Text(text = "Email: ${userData.email}")
        Text(text = "Image URL: ${userData.imageUrl}")
        Text(text = "UID: ${userData.uid}")
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