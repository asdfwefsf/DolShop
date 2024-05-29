package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.presentation.R

@Composable
fun SignUpScreen1(navController: NavController) {

    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier.size(24.dp)

            )
            Spacer(modifier = Modifier.weight(1f))
            Text("회원가입" , fontSize = 20.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .height(16.dp)
                    .background(Color(0xFFA8FF00))
            )
        }

        Text("회원가입에 필요한" , fontSize = 30.sp , color = Color.Black)
        Text("약관에 동의해주세요" , fontSize = 30.sp , color = Color.Black)

        Text(
            text = "SingUpScreen1",
            modifier = Modifier.clickable {
                navController.navigate(ScreenList.SignUpScreen2.route) {
                    popUpTo(ScreenList.SignUpScreen2.route) {
                        inclusive = true
                    }
                }

            }
        )
    }






}

