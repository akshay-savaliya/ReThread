package com.ags.rethread.domain.use_case.auth

import com.ags.rethread.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) =
        repository.loginUser(email, password)
}