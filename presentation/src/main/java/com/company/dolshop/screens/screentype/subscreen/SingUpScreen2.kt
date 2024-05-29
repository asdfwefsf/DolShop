package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList

@Composable
fun SingUpScreen2(navController: NavController)  {
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