package com.ags.rethread.domain.repository

import android.net.Uri
import com.ags.rethread.domain.model.UserModel

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Result<UserModel>
    suspend fun registerUser(imageUri: Uri, user: UserModel, password: String): Result<UserModel>
    fun logout()
    fun getCurrentUser(): UserModel?
}