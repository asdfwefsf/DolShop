package com.company.dolshop.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.usecase.KakaoLoginUseCase
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class KakaoLoginViewModel @Inject constructor(
    private val usecase : KakaoLoginUseCase
) : ViewModel() {

    suspend fun kakologin() {
        usecase()
    }
//    suspend fun handleKakaoLogin(): Boolean =
//        suspendCoroutine<Boolean> { continuation ->
//            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//                if (error != null) {
//                    Log.e("login", "카카오계정으로 로그인 실패", error)
//                    continuation.resume(false)
//                } else if (token != null) {
//                    Log.i("login", "카카오계정으로 로그인 성공 ${token.accessToken}")
//                    viewModelScope.launch {
////                        saveLoginState(true)
//                    }
//                    continuation.resume(true)
//                }
//            }
//
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(application.applicationContext)) {
//                UserApiClient.instance.loginWithKakaoTalk(application.applicationContext) { token, error ->
//                    if (error != null) {
//                        Log.e("login", "카카오톡으로 로그인 실패", error)
//
//                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
//                            return@loginWithKakaoTalk
//                        }
//
//                        UserApiClient.instance.loginWithKakaoAccount(application.applicationContext, callback = callback)
//                    } else if (token != null) {
//                        Log.i("login", "카카오톡으로 로그인 성공 ${token.accessToken}")
//                    }
//                }
//            } else {
//                UserApiClient.instance.loginWithKakaoAccount(application.applicationContext, callback = callback)
//            }
//        }



}