package com.ags.rethread.domain.use_case.auth

import com.ags.rethread.data.local.UserPreferencesManager
import com.ags.rethread.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val prefs: UserPreferencesManager
) {
    suspend operator fun invoke() {
        repository.logout()
        prefs.clearData()
    }
}