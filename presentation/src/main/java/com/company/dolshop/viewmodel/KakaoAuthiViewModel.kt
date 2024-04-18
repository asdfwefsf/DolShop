package com.company.dolshop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.usecase.KakaoLoginUseCase
import com.company.domain.usecase.KakaoLogoutUseCase
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class KakaoAuthiViewModel @Inject constructor(
    private val kakaoLoginUsecase : KakaoLoginUseCase,
    private val kakaoLogoutUsecase : KakaoLogoutUseCase
) : ViewModel() {

    val isLoggedIn = MutableStateFlow<Boolean>(false)

    private val _loginValue = MutableStateFlow<Boolean>(false)
    val loginValue = _loginValue

    fun kakaoaaaaLogin() {
        viewModelScope.launch {
            // 로그인 시도 및 결과를 handleKakaoLogin 함수에서 받아옵니다.
//            val loginResult = kakaoLoginUsecase()

            // 로그인이 성공했다면,
//            if (loginResult) {
            // 로그인 상태를 저장합니다.
//                saveLoginState(true)

            // 사용자 정보를 불러오는 함수를 호출합니다.
//                suspendGetAuthInfo(userInfoList)
//            }

            // 로그인 결과에 따라 로그인 상태를 업데이트합니다.
//            _loginValue.emit(loginResult)
        }
    }

    suspend fun kakaoLogin() {
        kakaoLoginUsecase().apply {
            if (this) {
                _loginValue.emit(true)
            }
//        }
//        val loginResult = kakaoLoginUsecase()
//        if (loginResult) {
//            _loginValue.emit(loginResult)
//        }
        }


    }

    suspend fun kakaoLogout() {
        kakaoLogoutUsecase()
    }
}

//    fun kakaoLogout() {
//        viewModelScope.launch {
//            if (handleKakaoLogout()) {
//                isLoggedIn.emit(false)
//            }
//        }
//    }
//
//    private suspend fun handleKakaoLogout(): Boolean =
//        suspendCoroutine { continuation ->
//            UserApiClient.instance.logout { error ->
//                if (error != null) {
//                    Log.e(TAG, "로그아웃 실패 , SDK에서 토큰 삭제됨", error)
//                    continuation.resume(false)
//                } else {
//                    Log.i(TAG, "로그아웃 성공 , SDK에서 토큰 삭제됨")
//                    continuation.resume(true)
//
//                }
//
//            }
//        }
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



