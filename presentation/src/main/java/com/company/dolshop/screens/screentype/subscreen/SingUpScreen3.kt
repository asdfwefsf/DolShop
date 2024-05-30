package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList

@Composable
fun SingUpScreen3(navController: NavController) {

    Column(
        modifier = Modifier.background(Color.White).fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier.size(24.dp).clickable {
                    navController.navigate(ScreenList.SignUpScreen2.route) {
                        popUpTo(ScreenList.SignUpScreen2.route) {
                            inclusive = true
                        }
                    }
                }

            )
            Spacer(modifier = Modifier.weight(1f))
            Text("회원가입", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(10.dp)
                    .background(Color(0xFFA8FF00))
            )
        }

        Text(
            "회원가입이",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
        )
        Text(
            "완료되었습니다.",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
        )
        Spacer(Modifier.size(10.dp))
        Text(
            "시작화면에서 로그인해주세요",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
        )
        Text(
            "최최 로그인 이후 접속 시, 자동 로그인이 활성화됩니다.",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 5.dp)
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.85f))

        Button(
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
            onClick = {
                navController.navigate(ScreenList.LoginScreen.route) {
                    popUpTo(ScreenList.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
        ) {
            Text( text = "로그인하기" )
        }

    }



}