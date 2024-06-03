package com.company.dolshop.screens.screentype.authscreen.signin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.AuthiViewModel
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.setLoginState
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun LoginScreen2(navController: NavController) {
    var kakaoEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val realtimeDB = Firebase.database
    val context = LocalContext.current

    val authiViewModel: AuthiViewModel = hiltViewModel()

    val dataStoreUtility = DataStoreUtility.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate(ScreenList.LoginScreen.route) {
                            popUpTo(ScreenList.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }

            )
            Spacer(modifier = Modifier.weight(1f))
            Text("로그인", fontSize = 20.sp, color = Color.Black)
            Spacer(modifier = Modifier.weight(1.1f))
        }

        Text(
            "로그인하기.",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp, top = 34.dp)
        )

        Column(
            modifier = Modifier.padding(top = 30.dp, start = 15.dp, end = 15.dp)
        ) {
            Text(
                "이메일 ",
                color = Color.Black,
                modifier = Modifier.padding(start = 2.dp)
            )
            OutlinedTextField(
                placeholder = { Text("이메일을 입력해주세요", color = Color.Black) },
                value = kakaoEmail,
                onValueChange = { kakaoEmail = it },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Green
                )

            )
        }

        Column(
            modifier = Modifier.padding(top = 10.dp, start = 15.dp, end = 15.dp)
        ) {
            Text(
                "비밀번호 ",
                color = Color.Black,
                modifier = Modifier.padding(start = 2.dp)
            )
            OutlinedTextField(
                placeholder = { Text("비밀번호를 입력해주세요", color = Color.Black) },
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Green
                )

            )
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 15.dp, end = 15.dp),
            onClick = {
                Log.d("sfljeo22323"  ,"1")
                    scope.launch {
                        Log.d("sfljeo22323"  ,"2")

                        authiViewModel.signInFirebaseAuth(kakaoEmail, password, context)
                        val userInfolist = authiViewModel.userInfoList
                        val loginValue = authiViewModel.loginValue

                        loginValue.collect { it ->
                            Log.d("sfslf" , "sfsf")
                            if (it) {
                                userInfolist.collect { userInfo ->
                                    if (userInfo.authNumber != "ds") {
                                        val userRef =
                                            realtimeDB.getReference("users/${userInfolist.value.authNumber}/kakaoAuth")
                                        val userData = mapOf(
                                            "authNumber" to userInfolist.value.authNumber,
                                            "authEmail" to userInfolist.value.authEmail,
                                            "authNickName" to userInfolist.value.authNicName,
                                            "authProfileImage" to userInfolist.value.authProfileImage,
                                            "address" to ""
                                        )
                                        userRef.setValue(userData)
                                    }

                                    dataStoreUtility.apply {
                                        context.setLoginState(true)
                                        Log.d("sfljeo22323"  ,"4")

                                    }
                                    navController.navigate(ScreenList.RocksScreen.route) {
                                        popUpTo(ScreenList.RocksScreen.route) {
                                            inclusive = true
                                        }
                                    }

                                }
                            }

                        }
//                        if (fireabaseAuthViewModel.loginValue.value) {
//                            Log.d("sfljeo22323"  ,"3")
//
//
//
//                        }

                    }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
        ) {
            Text(
                text = "로그인하기",
                color = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("계정이 없으신가요?" , color = Color.Gray)
            Spacer(Modifier.size(4.dp))
            Text("가입하기" , color = Color.Black , modifier = Modifier.clickable {
                navController.navigate(ScreenList.SignUpScreen1.route) {
                    popUpTo(ScreenList.LoginScreen.route) {
                        inclusive = true
                    }
                }
            })
        }


//        Row(
//            modifier = Modifier
//                .width(381.dp)
//                .background(Color(0xFF7BF579))
//                .padding(top = 30.dp, start = 15.dp, end = 15.dp)
//                .clickable {
//
//                }
//
//        ) {
//            Text("로그인하기", color = Color.Black)
//        }


//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 8.dp, end = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//
//        }

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 8.dp, end = 8.dp, top = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
////            Text("비밀번호", color = Color.Black, modifier = Modifier.weight(1f))
//            OutlinedTextField(
//                label = { Text("비밀번호") },
//                placeholder = { Text("비밀번호를 입력해주세요") },
//                value = password,
//                onValueChange = { password = it },
//                modifier = Modifier
//                    .weight(5f)
//                    .padding(start = 4.dp),
//                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color.White,
//                    unfocusedContainerColor = Color.White,
//                    unfocusedIndicatorColor = Color.Black,
//                    focusedIndicatorColor = Color.Green
//                )
//            )
//        }
    }


}