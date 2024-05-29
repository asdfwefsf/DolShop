package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
fun SingUpScreen2(navController: NavController) {


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
                    navController.navigate(ScreenList.SignUpScreen1.route) {
                        popUpTo(ScreenList.SignUpScreen1.route) {
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
                    .fillMaxWidth(0.66f)
                    .height(10.dp)
                    .background(Color(0xFFA8FF00))
            )
        }



        Text(
            text = "SingUpScreen2",
            modifier = Modifier.clickable {
                navController.navigate(ScreenList.SignUpScreen3.route) {
                    popUpTo(ScreenList.SignUpScreen3.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}