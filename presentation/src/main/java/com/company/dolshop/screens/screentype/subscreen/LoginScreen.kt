package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.presentation.R
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.setLoginState
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: KakaoAuthiViewModel) {

    val scope = rememberCoroutineScope()
    val realtimeDB = Firebase.database
    val context = LocalContext.current
    val dataStoreUtility = DataStoreUtility.getInstance()

    val lottie by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.jjinreal)
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Dol And Shop")
        LottieAnimation(
            composition = lottie,
            iterations = LottieConstants.IterateForever

        )
        Image(
            painter = painterResource(id = R.drawable.kakao_login_large_wide),
            contentDescription = "Login Icon",
            modifier = Modifier.clickable {
                scope.launch {
                    viewModel.kakaoLogin()
                    val userInfolist = viewModel.userInfoList
                    if (viewModel.loginValue.value) {
                        dataStoreUtility.apply {
                            context.setLoginState(true)
                        }

                        navController.navigate(ScreenList.MyPageScreen.route) {
                            popUpTo(ScreenList.MyPageScreen.route) {
                                inclusive = true
                            }
                        }
                    }

                    userInfolist.collect { userInfo ->
                        if (userInfo.authNumber != "s") {
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
                    }

                }
            }
        )
//        Spacer(modifier = Modifier.size(16.dp))
//        Button(onClick = {
//            scope.launch {
//                viewModel.kakaoLogin()
//                val userInfolist = viewModel.userInfoList
//                if (viewModel.loginValue.value) {
//
//                    val userRef =
//                        realtimeDB.getReference("users/${userInfolist.value.authNumber}/kakaoAuth")
//                    val userData = mapOf(
//                        "authNumber" to userInfolist.value.authNumber,
//                        "authEmail" to userInfolist.value.authEmail,
//                        "authNickName" to userInfolist.value.authNicName,
//                        "authProfileImage" to userInfolist.value.authProfileImage,
//                        "address" to ""
//                    )
//                    userRef.setValue(userData)
//
//                    dataStoreUtility.apply {
//                        context.setLoginState(true)
//                    }
//
//                    navController.navigate(ScreenList.MyPageScreen.route) {
//                        popUpTo(ScreenList.MyPageScreen.route) {
//                            inclusive = true
//                        }
//                    }
//                }
//            }
//
//        }) {
//            Image(
//                painter = painterResource(id = R.drawable.kakao_login_large_wide),
//                contentDescription = "Login Icon"
//            )
////            Spacer(modifier = Modifier.width(8.dp))
////            Text("로그인")
//        }
    }
}




