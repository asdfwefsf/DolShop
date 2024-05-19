package com.company.utility

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreUtility private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: DataStoreUtility? = null

        fun getInstance(): DataStoreUtility {
            return INSTANCE ?: synchronized(this) {
                val instance = DataStoreUtility()
                INSTANCE = instance
                instance
            }
        }

        private val Context.dataStore by preferencesDataStore(name = "login_Boolean")

        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

        suspend fun Context.setLoginState(isLoggedIn: Boolean) {
            dataStore.edit { preferences ->
                preferences[IS_LOGGED_IN] = isLoggedIn
            }
        }

        val Context.isLoggedInFlow
            get() = dataStore.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[IS_LOGGED_IN] ?: false
                }
    }
}
