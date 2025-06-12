package com.ags.rethread.domain.use_case.auth

import com.ags.rethread.data.local.UserPreferencesManager
import com.ags.rethread.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val preferencesManager: UserPreferencesManager
) {
    operator fun invoke(): Flow<UserModel> = preferencesManager.getUserData
}