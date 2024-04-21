package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import kotlinx.coroutines.launch

@Composable
fun MyPageScreen() {
    val scope = rememberCoroutineScope()
    val viewmodel : KakaoAuthiViewModel = hiltViewModel()
    val userInfoList = viewmodel.userInfoList.collectAsState().value
    Column {
        Button(onClick = {
            scope.launch {
                viewmodel.kakaoLogout()
            }
        }) {
            Text("logout")
        }
//        Button(onClick = {
//            scope.launch {
//                viewmodel.getUserInfo()
//            }
//        }) {
//            Text("logout")
//        }
        if(userInfoList != null) {

            Spacer(Modifier.size(8.dp))
            Text(userInfoList.authEmail)
            Spacer(Modifier.size(8.dp))
            Text(userInfoList.authNicName)
            Spacer(Modifier.size(8.dp))
            Text(userInfoList.authNumber)
            Spacer(Modifier.size(8.dp))
            Text(userInfoList.authProfileImage)

        }

    }


}