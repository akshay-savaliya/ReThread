package com.ags.rethread.presentation.screen.auth

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ags.rethread.domain.model.UserModel
import com.ags.rethread.domain.use_case.auth.LoginUserUseCase
import com.ags.rethread.domain.use_case.auth.LogoutUseCase
import com.ags.rethread.domain.use_case.auth.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _authEvent = MutableSharedFlow<AuthEvent>()
    val authEvent = _authEvent.asSharedFlow()


    fun onLoginClick() {
        viewModelScope.launch {
            _authEvent.emit(AuthEvent.NavigateToLogin)
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _authEvent.emit(AuthEvent.NavigateToRegister)
        }
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUserUseCase(email, password)
            _isLoading.value = false

            _loginState.value = if (result.isSuccess) {
                LoginUiState.Success
            } else {
                LoginUiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun register(imageUri: Uri, user: UserModel, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = registerUserUseCase(imageUri, user, password)
            _isLoading.value = false

            _loginState.value = if (result.isSuccess) {
                LoginUiState.Success
            } else {
                LoginUiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun logout() {
        logoutUseCase()
    }

    fun resetLoginState() {
        _loginState.value = LoginUiState.Idle
    }
}