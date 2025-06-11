package com.ags.rethread.presentation.screen.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ags.rethread.R
import com.ags.rethread.presentation.components.CustomFilledButton
import com.ags.rethread.presentation.components.CustomTextButton
import com.ags.rethread.presentation.components.PasswordInputField
import com.ags.rethread.presentation.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val isLoading by viewModel.isLoading.collectAsState()
    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginUiState.Success -> {
                navController.navigate(Routes.Main.route) {
                    popUpTo(Routes.Auth.route) { inclusive = true }
                    launchSingleTop = true
                }
                viewModel.resetLoginState() // reset state after navigation
            }

            is LoginUiState.Error -> {
                val errorMessage = (loginState as LoginUiState.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.resetLoginState() // reset state after showing error
            }

            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        viewModel.authEvent.collect { event ->
            when (event) {
                AuthEvent.NavigateToRegister -> {
                    navController.navigate(Routes.Register.route) {
                        popUpTo(Routes.Auth.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }

                else -> Unit
            }
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()) // Enable scrolling
                .systemBarsPadding()
                .imePadding(), // 👈 Handles keyboard overlap
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = "Login Banner",
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f),
                tint = MaterialTheme.colorScheme.surfaceTint
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                enabled = !isLoading,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            PasswordInputField(
                password = password,
                enabled = !isLoading,
                onPasswordChange = { password = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomFilledButton(
                isLoading = isLoading,
                text = "Log In",
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Please enter email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@CustomFilledButton
                    }
                    viewModel.login(email, password)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextButton(
                isLoading = false,
                enabled = !isLoading,
                text = "Don't have an account? Sign Up",
                onClick = {
                    viewModel.onSignUpClick() // Emit event to navigate
                }
            )
        }
    }
}