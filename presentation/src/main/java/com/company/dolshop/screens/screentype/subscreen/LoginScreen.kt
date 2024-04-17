package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList

@Composable
fun LoginScreen(navController: NavController) {
    Button(onClick = {
        // 로그인 로직 수행 후 성공적으로 로그인 되었다고 가정
        // 로그인 성공 후 메인 화면으로 이동
        navController.navigate(ScreenList.MyPageScreen.route) {
            // 현재 로그인 화면을 백스택에서 제거하여 뒤로 가기 했을 때 로그인 화면으로 돌아가지 않도록 설정
            popUpTo(ScreenList.LoginScreen.route) {
                inclusive = true
            }
        }
    }) {
        Text("로그인")
    }
}