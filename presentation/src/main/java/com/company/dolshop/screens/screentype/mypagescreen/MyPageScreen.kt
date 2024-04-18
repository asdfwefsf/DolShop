package com.company.dolshop.screens.screentype.mypagescreen

import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import kotlinx.coroutines.launch

@Composable
fun MyPageScreen(viewModel: KakaoAuthiViewModel) {
    val scope = rememberCoroutineScope()
    Button(onClick = {
        scope.launch {
            viewModel.kakaoLogout()
        }
    }) {

    }

    Text("logout")
}