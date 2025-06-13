package com.ags.rethread.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ags.rethread.domain.model.UserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val PREF_NAME = "user_prefs"
val Context.dataStore by preferencesDataStore(name = PREF_NAME)

class UserPreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val KEY_IMAGE_URL = stringPreferencesKey("image_url")
        val KEY_NAME = stringPreferencesKey("name")
        val KEY_USERNAME = stringPreferencesKey("username")
        val KEY_BIO = stringPreferencesKey("bio")
        val KEY_EMAIL = stringPreferencesKey("email")
        val KEY_UID = stringPreferencesKey("uid")
    }

    suspend fun storeUserData(user: UserModel) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IMAGE_URL] = user.imageUrl
            prefs[KEY_NAME] = user.name
            prefs[KEY_USERNAME] = user.username
            prefs[KEY_BIO] = user.bio
            prefs[KEY_EMAIL] = user.email
            prefs[KEY_UID] = user.uid
        }
    }

    val getUserData: Flow<UserModel> = context.dataStore.data.map { prefs ->
        UserModel(
            imageUrl = prefs[KEY_IMAGE_URL] ?: "",
            name = prefs[KEY_NAME] ?: "",
            username = prefs[KEY_USERNAME] ?: "",
            bio = prefs[KEY_BIO] ?: "",
            email = prefs[KEY_EMAIL] ?: "",
            uid = prefs[KEY_UID] ?: ""
        )
    }

    suspend fun clearData() {
        context.dataStore.edit { it.clear() }
    }
}