package com.company.dolshop.screens.screentype.authscreen.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.auth.AuthiViewModel
import com.company.presentation.R
import com.company.utility.datastore.DataStoreUtility
import com.company.utility.datastore.DataStoreUtility.Companion.setLoginState
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthiViewModel) {

    val scope = rememberCoroutineScope()
    val realtimeDB = Firebase.database
    val context = LocalContext.current
    val dataStoreUtility = DataStoreUtility.getInstance()

//    val fireabaseAuthViewModel: FirebaseAuthViewModel = hiltViewModel()
    val authiViewModel: AuthiViewModel = hiltViewModel()

    val lottie by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.jjinreal)
    )

    var kakaoEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val loginValue by authiViewModel.loginValue.collectAsState()

    LaunchedEffect(loginValue) {
        if (loginValue) {
            context.setLoginState(true)
            navController.navigate(ScreenList.RocksScreen.route) {
                popUpTo(ScreenList.RocksScreen.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Dol And Shop", color = Color.Black , modifier = Modifier.padding(top = 4.dp))

        LottieAnimation(
            composition = lottie,
            iterations = LottieConstants.IterateForever,
        )

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 8.dp, end = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text("이메일 ", color = Color.Black, modifier = Modifier.weight(1f))
//            OutlinedTextField(
//                value = kakaoEmail,
//                onValueChange = { kakaoEmail = it },
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
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 8.dp, end = 8.dp, top = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text("비밀번호", color = Color.Black, modifier = Modifier.weight(1f))
//            OutlinedTextField(
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

        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "",
            modifier = Modifier.clickable {
                navController.navigate(ScreenList.LoginScreen2.route)
            }
//            modifier = Modifier
//                .padding(top = 8.dp)
//                .clickable {
//                    scope.launch {
//                        fireabaseAuthViewModel.signInFirebaseAuth(kakaoEmail, password, context)
//                        val userInfolist = fireabaseAuthViewModel.userInfoList
//                        userInfolist.collect { userInfo ->
//                            if (userInfo.authNumber != "s") {
//                                val userRef =
//                                    realtimeDB.getReference("users/${userInfolist.value.authNumber}/kakaoAuth")
//                                val userData = mapOf(
//                                    "authNumber" to userInfolist.value.authNumber,
//                                    "authEmail" to userInfolist.value.authEmail,
//                                    "authNickName" to userInfolist.value.authNickName,
//                                    "authProfileImage" to userInfolist.value.authProfileImage,
//                                    "address" to ""
//                                )
//                                userRef.setValue(userData)
//                            }
//                        }
//                    }
//                }
        )

//        Button(
//            modifier = Modifier.width(400.dp),
//            onClick = {
//
//            }
//
//        ) {
//            Text("로그인", color = Color.Black)
//        }

        Image(
            painter = painterResource(id = R.drawable.kakao_start_real),
            contentDescription = "Login Icon",
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
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
                                    "authNickName" to userInfolist.value.authNickName,
                                    "authProfileImage" to userInfolist.value.authProfileImage,
                                    "address" to ""
                                )
                                userRef.setValue(userData)
                            }
                        }
                    }
                }
        )

        Image(painter = painterResource(id = R.drawable.or), contentDescription = "")

        Row {
            Spacer(Modifier.size(10.dp))
            Image(painter = painterResource(id = R.drawable.signup), contentDescription = "",
                modifier = Modifier.clickable {
                    navController.navigate(ScreenList.SignUpScreen1.route)
                }
            )
            Spacer(Modifier.size(30.dp))
            Image(painter = painterResource(id = R.drawable.findauth), contentDescription = "")
            Spacer(Modifier.size(30.dp))
            Image(painter = painterResource(id = R.drawable.calltome), contentDescription = "")
            Spacer(Modifier.size(10.dp))
        }

    }
}




