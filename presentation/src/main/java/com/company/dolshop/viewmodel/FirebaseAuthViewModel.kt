package com.company.dolshop.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.firebase.SaverFirebaseAuthRepository
import com.company.domain.usecase.firebase.SaverFirebaseAuthUseCase
import com.company.domain.usecase.kakao.UpdateKakaoUserInfoUseCase
import com.company.domain.usecase.kakao.getUserKakaoInfoUseCase
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    // SaverFirebaseAuthUseCase가 회원정보를 UserInfo에 저장
    private val SaverFirebaseAuthUseCase: SaverFirebaseAuthUseCase,
    private val UpdateKakaoUserInfoUseCase: UpdateKakaoUserInfoUseCase
) : ViewModel() {
    private val _userInfoList = MutableStateFlow<DomainUserInfoModel>(
        DomainUserInfoModel("s", "s", "s", "s")
    )
    val userInfoList: MutableStateFlow<DomainUserInfoModel> = _userInfoList

    private val _loginValue = MutableStateFlow<Boolean>(false)
    val loginValue = _loginValue.asStateFlow()

    // 파이어베이스 회원가입
    // 여기서 유저 정보 Room DB에 저장
//    fun signUpFirebaseAuth(
//        kakaoEmail: String,
//        password: String,
//        name: String,
//        phoneNumber: String,
//        context: Context,
//        domainUserInfoModel: DomainUserInfoModel
//    ) {
//        Firebase.auth.createUserWithEmailAndPassword(kakaoEmail, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val currentUser = Firebase.auth.currentUser?.uid
//
//                    Toast.makeText(context, "회원가입에 성공했습니다. 로그인해주세요.", Toast.LENGTH_SHORT).show()
//                    CoroutineScope(Dispatchers.IO).launch {
//                        SaverFirebaseAuthUseCase(domainUserInfoModel, currentUser.toString())
//
//                    }
//                } else {
//                    Toast.makeText(context, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }

    // 파이어베이스 로그인
//    suspend fun signInFirebaseAuth(kakaoEmail: String, password: String, context: Context) {
//        Firebase.auth.signInWithEmailAndPassword(kakaoEmail, password)
//            .addOnCompleteListener() { task ->
//                val currentUser = Firebase.auth.currentUser
//                if (task.isSuccessful && currentUser != null) {
//                    viewModelScope.launch {
//                        withContext(Dispatchers.IO) {
//                            _loginValue.emit(true)
//                        }
//                    }
//                    Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .addOnFailureListener {
//                Log.d("test", "에러났음")
//            }
//    }

//    fun emailConfirm(email: String) {
//        val actionCodeSettings = ActionCodeSettings.newBuilder()
//            .setUrl("https://dolshop.page.link/eNh4")
//            .setHandleCodeInApp(true)
//            .setAndroidPackageName(
//                "com.company.dolshop",
//                true,
//                "1"
//            )
//            .build()
//
//        Firebase.auth.sendSignInLinkToEmail(email, actionCodeSettings)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Log.d("emailConfirm", "ok")
//                } else {
//                    Log.d("emailConfirm", "no${task.exception?.message}")
//                }
//            }
//    }

    init {
        viewModelScope.launch {
            UpdateKakaoUserInfoUseCase().collect { userinfo ->
                _userInfoList.value = userinfo
            }

        }
    }
}


