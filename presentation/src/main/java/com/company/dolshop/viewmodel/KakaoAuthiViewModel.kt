package com.company.dolshop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.UserInfoModel
import com.company.domain.usecase.KakaoLoginUseCase
import com.company.domain.usecase.KakaoLogoutUseCase
import com.company.domain.usecase.UpdateKakaoUserInfoUseCase
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
    private val kakaoLoginUsecase: KakaoLoginUseCase,
    private val kakaoLogoutUsecase: KakaoLogoutUseCase,
    private val getUserKakaoInfoUseCase: getUserKakaoInfoUseCase,
    private val updateUserKakaoInfoUseCase: UpdateKakaoUserInfoUseCase,

    ) : ViewModel() {


    // 로그인 여부 확인
    private val _loginValue = MutableStateFlow<Boolean>(false)
    val loginValue = _loginValue

    // 카카오 로그인 기능
    suspend fun kakaoLogin() {
//        val sibal = kakaoLoginUsecase()
//        if (sibal) {
//            getUserKakaoInfo()
//        }
//        _loginValue.emit(true)

        kakaoLoginUsecase().apply {
            if (this) {
                getUserKakaoInfo()
            }
            _loginValue.emit(true)

        }
    }

    // 카카오 로그아웃 기능
    suspend fun kakaoLogout() {
        viewModelScope.launch {
            kakaoLogoutUsecase()

        }
    }

    // 유저의 카카오 정보 받아오기
    // 사용자 정보 반환 관련 ViewModel
    private val _userInfoList = MutableStateFlow<UserInfoModel>(
        UserInfoModel("s" , "s", "s" , "s" )
    )
    val userInfoList = _userInfoList

    private suspend fun getUserKakaoInfo() {
        getUserKakaoInfoUseCase()
    }

    init {
        viewModelScope.launch {
            kakaoInfoUpdate()
        }
    }
    suspend fun kakaoInfoUpdate() {
        updateUserKakaoInfoUseCase().collect {userInfo ->
            _userInfoList.value = userInfo
        }
    }


}


