package com.ags.rethread.domain.use_case.auth

import android.net.Uri
import com.ags.rethread.domain.model.UserModel
import com.ags.rethread.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(imageUri: Uri, user: UserModel, password: String) =
        repository.registerUser(imageUri, user, password)
}