package com.company.dolshop.viewmodel.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.DomainUserInfoModel
import com.company.domain.usecase.firebase.SaverFirebaseAuthUseCase
import com.company.domain.usecase.kakao.GetUserInfoDbUseCase
import com.company.domain.usecase.kakao.GetUserKakaoInfoUseCase
import com.company.domain.usecase.kakao.KakaoLoginUseCase
import com.company.domain.usecase.kakao.KakaoLogoutUseCase
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// 기존에 KakaoAuthViewModel -> AuthViewModel
@HiltViewModel
// 카카오 로그인 및 회원가입 , 로그아웃
class AuthiViewModel @Inject constructor(
    private val kakaoLoginUsecase: KakaoLoginUseCase,
    private val kakaoLogoutUsecase: KakaoLogoutUseCase,

    // 카카오 api로부터 카카오 정보 가져와서 UserInfo에 넣어주는 역할
    private val getUserKakaoInfoUseCase: GetUserKakaoInfoUseCase,

    // 파이어베이스를 통해 회원가입 할 때 해당 유저 정보를 UserInfo에 저장하는 역할
    private val saverFirebaseAuthUseCase: SaverFirebaseAuthUseCase,

    // UserInfo에 저장된 유저정보 가져오는 역할
    private val getUserInfoDBUseCase: GetUserInfoDbUseCase
) : ViewModel() {


    // 로그인 여부 확인
    private val _loginValue = MutableStateFlow<Boolean>(false)
    val loginValue = _loginValue

    // 카카오 로그인 및 회원가입 기능
    suspend fun kakaoLogin() {
        kakaoLoginUsecase().apply {
            if (this) {
                getUserKakaoInfo()
            }
            _loginValue.emit(true)

        }
    }

    private suspend fun getUserKakaoInfo() {
        getUserKakaoInfoUseCase()
    }

    // 카카오 로그아웃 및 앱 종료 기능
    suspend fun kakaoLogout() {
        viewModelScope.launch {
            kakaoLogoutUsecase()

        }
    }

    // 파이어베이스 로그인
    suspend fun signInFirebaseAuth(kakaoEmail: String, password: String, context: Context) {
        Firebase.auth.signInWithEmailAndPassword(kakaoEmail, password)
            .addOnCompleteListener() { task ->
                val currentUser = Firebase.auth.currentUser
                if (task.isSuccessful && currentUser != null) {
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            _loginValue.emit(true)
                        }
                    }
                    Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Log.d("test", "에러났음")
            }
    }

    // 파이어베이스 회원가입
    // 여기서 유저 정보 Room DB에 저장
    fun signUpFirebaseAuth(
        kakaoEmail: String,
        password: String,
        name: String,
        phoneNumber: String,
        context: Context,
        domainUserInfoModel: DomainUserInfoModel
    ) {
        Firebase.auth.createUserWithEmailAndPassword(kakaoEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = Firebase.auth.currentUser?.uid

                    Toast.makeText(context, "회원가입에 성공했습니다. 로그인해주세요.", Toast.LENGTH_SHORT).show()
                    CoroutineScope(Dispatchers.IO).launch {
                        saverFirebaseAuthUseCase(domainUserInfoModel, currentUser.toString())

                    }
                } else {
                    Toast.makeText(context, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 파이어베이스 이메일 인증
    fun emailConfirm(email: String) {
        val actionCodeSettings = ActionCodeSettings.newBuilder()
            .setUrl("https://dolshop.page.link/eNh4")
            .setHandleCodeInApp(true)
            .setAndroidPackageName(
                "com.company.dolshop",
                true,
                "1"
            )
            .build()

        Firebase.auth.sendSignInLinkToEmail(email, actionCodeSettings)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("emailConfirm", "ok")
                } else {
                    Log.d("emailConfirm", "no${task.exception?.message}")
                }
            }
    }


    // 사용자 정보(파이어베이스 로그인 , 카카오 로그인)
    // 파이어베이스 회원 가입 할 때 , 카카오 시작하기 할 때 => UserInfo에 사용자 정보 저장
    private val _userInfoList = MutableStateFlow<DomainUserInfoModel>(
        DomainUserInfoModel("s", "s", "s", "s")
    )
    val userInfoList: MutableStateFlow<DomainUserInfoModel> = _userInfoList

    private suspend fun getUserInfoDB() {
        getUserInfoDBUseCase().collect { userInfo ->
            _userInfoList.value = userInfo
        }
    }

    init {
        viewModelScope.launch {
            getUserInfoDB()
        }
    }

}


