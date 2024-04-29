package com.company.dolshop.screens.screentype.subscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.company.designsystem.designsystem.Paddings
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: KakaoAuthiViewModel) {
    val scope = rememberCoroutineScope()

    val realtimeDB = Firebase.database
    val myRef = realtimeDB.getReference("message")
    myRef.setValue("Hello , World!")
    var text by remember { mutableStateOf("") }
    Column {
        Button(onClick = {
            scope.launch {
                viewModel.kakaoLogin()
                if (viewModel.loginValue.value) {
                    navController.navigate(ScreenList.MyPageScreen.route) {
                        popUpTo(ScreenList.MyPageScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }

        }) {
            Text(
                "로그인"
            )
        }
        Spacer(modifier = Modifier.padding(com.company.designsystem.designsystem.Paddings.extra))
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            }
        )
        Spacer(modifier = Modifier.padding(com.company.designsystem.designsystem.Paddings.extra))
        Button(
            onClick = { myRef.setValue(text) }
        ) {
            Text(text = "Test")
        }
    }


}

