package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList

@Composable
fun SingUpScreen3(navController: NavController) {
    Text(
        text = "SingUpScreen3",
        modifier = Modifier.clickable {
            navController.navigate(ScreenList.LoginScreen.route) {
                popUpTo(ScreenList.LoginScreen.route) {
                    inclusive = true
                }
            }
        }

        )
}