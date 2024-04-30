package com.company.dolshop.screens.screentype.mypagescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList

@Composable
@ExperimentalMaterial3Api

fun ToAddressScreen(navController: NavController) {
    Column {

        TextField(value = "", onValueChange = {navController.navigate(ScreenList.AddressScreen.route)} ,
            textStyle = TextStyle(Color.Black)
//            modifier = Modifier.clickable {
//            navController.navigate(ScreenList.AddressScreen.route)
//        }

        )

    }

}