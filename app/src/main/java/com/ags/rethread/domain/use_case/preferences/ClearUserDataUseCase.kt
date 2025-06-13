package com.ags.rethread.domain.use_case.preferences

import com.ags.rethread.data.local.UserPreferencesManager
import javax.inject.Inject

class ClearUserDataUseCase @Inject constructor(
    private val preferencesManager: UserPreferencesManager
) {
    suspend operator fun invoke() {
        preferencesManager.clearData()
    }
}