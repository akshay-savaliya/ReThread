package com.ags.rethread.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ags.rethread.domain.use_case.auth.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<SplashEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        startSplashSequence()
    }

    private fun startSplashSequence() {
        viewModelScope.launch {
            delay(2500L) // Splash screen display time
            val user = getCurrentUserUseCase()

            if (user != null) {
                _eventFlow.emit(SplashEvent.NavigateToMain)
            } else {
                _eventFlow.emit(SplashEvent.NavigateToAuth)
            }
        }
    }
}