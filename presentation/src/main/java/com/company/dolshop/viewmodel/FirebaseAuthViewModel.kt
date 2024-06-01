package com.company.dolshop.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.firebase.SaverFirebaseAuthRepository
import com.company.domain.usecase.firebase.SaverFirebaseAuthUseCase
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    private val SaverFirebaseAuthUseCase : SaverFirebaseAuthUseCase
) : ViewModel() {
    private val _userInfoList = MutableStateFlow<DomainUserInfoModel>(
        DomainUserInfoModel("s", "s", "s", "s")
    )
    val userInfoList : MutableStateFlow<DomainUserInfoModel> = _userInfoList

    private val _loginValue = MutableStateFlow<Boolean>(false)
    val loginValue = _loginValue

    // 파이어베이스 회원가입
    fun signUpFirebaseAuth(kakaoEmail: String, password: String, name : String, phoneNumber : String , context: Context , domainUserInfoModel: DomainUserInfoModel) {
        Firebase.auth.createUserWithEmailAndPassword(kakaoEmail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = Firebase.auth.currentUser?.uid

                    Toast.makeText(context, "회원가입에 성공했습니다. 로그인해주세요.", Toast.LENGTH_SHORT).show()
                    CoroutineScope(Dispatchers.IO).launch {
                        SaverFirebaseAuthUseCase(domainUserInfoModel , currentUser.toString())

                    }
                } else {
                    Toast.makeText(context, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 파이어베이스 로그인
    suspend fun signInFirebaseAuth(kakaoEmail: String, password: String, context: Context) {
        Firebase.auth.signInWithEmailAndPassword(kakaoEmail, password)
            .addOnCompleteListener() { task ->
                val currentUser = Firebase.auth.currentUser
                if (task.isSuccessful && currentUser != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        _loginValue.emit(true)
                    }
                    val userId = Firebase.auth.currentUser!!.uid
                    Log.d("test", userId)
//                    Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Log.d("test", "에러났음")
            }
    }

    fun emailConfirm(email : String) {
        val actionCodeSettings = ActionCodeSettings.newBuilder()
            .setUrl("https://dolshop.page.link/eNh4")
            .setHandleCodeInApp(true)
            .setAndroidPackageName(
                "com.company.dolshop",
                true,
                "1"  )
            .build()

        Firebase.auth.sendSignInLinkToEmail(email, actionCodeSettings)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d("emailConfirm" , "ok")
                } else {
                    Log.d("emailConfirm" , "no${task.exception?.message}")
                }
            }
    }
}


