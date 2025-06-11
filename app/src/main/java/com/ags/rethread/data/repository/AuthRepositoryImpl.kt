package com.ags.rethread.data.repository

import android.net.Uri
import com.ags.rethread.domain.model.UserModel
import com.ags.rethread.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage
) : AuthRepository {

    override suspend fun loginUser(email: String, password: String): Result<UserModel> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = firebaseAuth.currentUser
            if (user != null) {
                Result.success(UserModel(uid = user.uid, email = user.email ?: ""))
            } else {
                Result.failure(Exception("No user found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun registerUser(
        imageUri: Uri,
        user: UserModel,
        password: String
    ): Result<UserModel> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(user.email, password).await()
            val uid = firebaseAuth.currentUser?.uid
                ?: return Result.failure(Exception("Registration failed"))
            val imageRef = firebaseStorage.reference.child("users/$uid.jpg")
            imageRef.putFile(imageUri).await()
            val imageUrl = imageRef.downloadUrl.await().toString()
            val newUser = user.copy(imageUrl = imageUrl, uid = uid)
            firebaseDatabase.reference.child("users").child(uid).setValue(newUser).await()
            Result.success(newUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): UserModel? {
        val user = firebaseAuth.currentUser
        return user?.let { UserModel(uid = it.uid, email = it.email ?: "") }
    }
}