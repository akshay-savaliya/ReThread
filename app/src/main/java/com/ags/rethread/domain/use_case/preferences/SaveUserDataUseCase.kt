package com.ags.rethread.domain.use_case.preferences

import com.ags.rethread.data.local.UserPreferencesManager
import com.ags.rethread.domain.model.UserModel
import javax.inject.Inject

class SaveUserDataUseCase @Inject constructor(
    private val preferencesManager: UserPreferencesManager
) {
    suspend operator fun invoke(user: UserModel) {
        preferencesManager.storeUserData(
            UserModel(
                imageUrl = user.imageUrl,
                name = user.name,
                username = user.username,
                bio = user.bio,
                email = user.email,
                uid = user.uid
            )
        )
    }
}