package com.company.utility

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// Datastore는 딱한개만 있어야 되서 싱글톤으로 만들어야 된다. 아니면 에러남 ㅋ
// private constructor() : 생성자를 private하게 해서 외부에서 객체 생성 못하게 막아
// companion object :
class DataStoreUtility private constructor() {
    companion object {
        // @Violatile 때문에 어디서 접근하든 동일한 DataStoreUtility에 접근
        @Volatile
        private var INSTANCE: DataStoreUtility? = null

        fun getInstance(): DataStoreUtility {
            return INSTANCE ?: synchronized(this) {
                val instance = DataStoreUtility()
                INSTANCE = instance
                instance
            }
        }

        // ** 로그인 여부 **
        // 로그인 했는지 안 했는지 저장하는 DataStore : 로그인 되어 있으면 자동 로그인 , 아니면 로그인 별도로 해야 되고 , 로그아웃 하면 로그인 정보 사라져.
        private val Context.dataStoreLogine by preferencesDataStore(name = "login_Boolean")

        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

        // setLoginState()의 parameter에 들가는 Boolean 값이 IS_LOGGED_IN에 저장되서 isLoggedInFlow가 방출될때 방출된다. 해당 값이 그래서
        // 자동 로그인 여부 판단해서 앱에서 자동 로그인을 구현 할 수 있음.
        suspend fun Context.setLoginState(isLoggedIn: Boolean) {
            dataStoreLogine.edit { preferences ->
                preferences[IS_LOGGED_IN] = isLoggedIn
            }
        }

        val Context.isLoggedInFlow : Flow<Boolean>
            get() = dataStoreLogine.data
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


        // ** 쿠퐂1 저장 여부 **
        private val Context.dataStoreCoupon1 by preferencesDataStore(name = "coupon_Boolean1")
        private val IS_COUPON1 = booleanPreferencesKey("is_coupon1")
        suspend fun Context.setCoupon1State(isCoupon: Boolean) {
            dataStoreCoupon1.edit { preferences ->
                preferences[IS_COUPON1] = isCoupon
            }
        }
        val Context.isCoupon1Flow : Flow<Boolean>
            get() = dataStoreCoupon1.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[IS_COUPON1] ?: false
                }


        // ** 쿠퐂2 저장 여부 **
        private val Context.dataStoreCoupon2 by preferencesDataStore(name = "coupon_Boolean2")
        private val IS_COUPON2 = booleanPreferencesKey("is_coupon2")
        suspend fun Context.setCoupon2State(isCoupon: Boolean) {
            dataStoreCoupon2.edit { preferences ->
                preferences[IS_COUPON2] = isCoupon
            }
        }
        val Context.isCoupon2Flow : Flow<Boolean>
            get() = dataStoreCoupon2.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[IS_COUPON2] ?: false
                }

    }
}
