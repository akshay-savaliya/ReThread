package com.ags.rethread.domain.use_case.auth

import com.ags.rethread.data.local.UserPreferencesManager
import com.ags.rethread.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val prefs: UserPreferencesManager
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        val result = repository.loginUser(email, password)
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