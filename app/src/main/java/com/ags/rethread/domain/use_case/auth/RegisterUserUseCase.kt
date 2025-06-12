package com.ags.rethread.domain.use_case.auth

import android.net.Uri
import com.ags.rethread.data.local.UserPreferencesManager
import com.ags.rethread.domain.model.UserModel
import com.ags.rethread.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val prefs: UserPreferencesManager
) {
    suspend operator fun invoke(imageUri: Uri, user: UserModel, password: String): Result<Unit> {
        val result = repository.registerUser(imageUri, user, password)
        return if (result.isSuccess) {
            val user = result.getOrNull()
            if (user != null) {
                prefs.storeUserData(user)
                Result.success(Unit)
            } else {
                Result.failure(Exception("User data is null"))
            }
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Unknown error"))
        }
    }
}