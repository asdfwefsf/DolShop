package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.DomainUserInfoModel
import com.company.domain.usecase.kakao.KakaoLoginUseCase
import com.company.domain.usecase.kakao.KakaoLogoutUseCase
import com.company.domain.usecase.kakao.UpdateKakaoUserInfoUseCase
import com.company.domain.usecase.kakao.getUserKakaoInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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
    private val _userInfoList = MutableStateFlow<DomainUserInfoModel>(
        DomainUserInfoModel("s", "s", "s", "s")
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
        updateUserKakaoInfoUseCase().collect { userInfo ->
            _userInfoList.value = userInfo
        }
    }


}


