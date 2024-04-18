package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.clickable
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
    val userInfoList = viewmodel.userInfoList.collectAsState()
    Column {
        Button(onClick = {
            scope.launch {
                viewmodel.kakaoLogout()
            }
        }) {
            Text("logout")
        }
        Log.d("coupang" , userInfoList.value[0].toString())
        Log.d("coupang" , userInfoList.value[1].toString())
        Log.d("coupang" , userInfoList.value[2].toString())
        Log.d("coupang" , userInfoList.value[3].toString())
        if(userInfoList.value.isNotEmpty()) {
            Log.d("coupangs" , userInfoList.value[0].toString())
            Log.d("coupangs" , userInfoList.value[1].toString())
            Log.d("coupangs" , userInfoList.value[2].toString())
            Log.d("coupangs" , userInfoList.value[3].toString())

            Spacer(Modifier.size(8.dp))
            Text(userInfoList.value[0])
            Spacer(Modifier.size(8.dp))
            Text(userInfoList.value[1])
            Spacer(Modifier.size(8.dp))
            Text(userInfoList.value[2])

        }

    }


}