package com.ags.rethread.presentation.screen.splash

sealed class SplashEvent {
    object NavigateToAuth : SplashEvent()
    object NavigateToMain : SplashEvent()
}