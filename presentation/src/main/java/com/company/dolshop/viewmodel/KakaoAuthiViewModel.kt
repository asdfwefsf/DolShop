package com.company.dolshop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.usecase.KakaoLoginUseCase
import com.company.domain.usecase.KakaoLogoutUseCase
import com.company.domain.usecase.getUserKakaoInfoUseCase
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
    private val kakaoLogoutUsecase : KakaoLogoutUseCase,
    private val getUserKakaoInfoUseCase: getUserKakaoInfoUseCase
) : ViewModel() {


    // 로그인 여부 확인
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

    // 카카오 로그인 기능
    suspend fun kakaoLogin() {
        kakaoLoginUsecase().apply {
            if (this) {
//                getUserKakaoInfo()
                _loginValue.emit(true)
                getUserKakaoInfo()
                Log.d("auth viewmodel" , userInfoList.value[0])
            }
//            getUserKakaoInfo()
        }
    }

    // 카카오 로그아웃 기능
    suspend fun kakaoLogout() {
        kakaoLogoutUsecase()
    }

    // 유저의 카카오 정보 받아오기

    // 사용자 정보 반환 관련 ViewModel
    private val _userInfoList = MutableStateFlow<List<String>>(
        listOf("a" , "b" , "c" , "d")
    )
    val userInfoList = _userInfoList
    suspend fun getUserKakaoInfo() {
        getUserKakaoInfoUseCase(userInfoList).ma{

        }
    }
//    init {
//        viewModelScope.launch {
//            getUserKakaoInfoUseCase(userInfoList)
//        }
//    }

}


