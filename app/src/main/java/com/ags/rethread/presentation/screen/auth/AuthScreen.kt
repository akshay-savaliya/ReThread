package com.ags.rethread.presentation.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ags.rethread.R
import com.ags.rethread.presentation.components.CustomFilledButton
import com.ags.rethread.presentation.components.CustomOutlinedButton
import com.ags.rethread.presentation.components.DividerWithText
import com.ags.rethread.presentation.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(navController: NavHostController) {

    var isLoginLoading by remember { mutableStateOf(false) }
    var isSignUpLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()) // Enable scrolling
                .systemBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile_authentication),
                contentDescription = "profile authentication icon",
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "One step away from awesome.",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Ready to weave some fun? Let’s get started!",
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomFilledButton(
                isLoading = isLoginLoading,
                text = "Log In",
                onClick = {
                    isLoginLoading = true
                    scope.launch {
                        navController.navigate(Routes.Login.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )

            DividerWithText("OR")

            CustomOutlinedButton(
                isLoading = isSignUpLoading,
                text = "Sign Up",
                onClick = {
                    isSignUpLoading = true
                    scope.launch {
                        navController.navigate(Routes.Register.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}