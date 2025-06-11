package com.ags.rethread.presentation.screen.auth

sealed class AuthEvent {
    object NavigateToLogin : AuthEvent()
    object NavigateToRegister : AuthEvent()
}