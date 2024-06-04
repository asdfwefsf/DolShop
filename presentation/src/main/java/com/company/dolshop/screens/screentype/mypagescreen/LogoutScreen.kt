package com.company.dolshop.screens.screentype.mypagescreen

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.auth.AuthiViewModel
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.setLoginState
import kotlinx.coroutines.launch

@Composable
fun LogoutScreen() {
    val scope = rememberCoroutineScope()
    val viewmodel : AuthiViewModel = hiltViewModel()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dataStoreUtility = DataStoreUtility.getInstance()

    if (showDialog) {
        AlertDialog(
            containerColor = Color.White,
            modifier = Modifier.background(Color.White),
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            viewmodel.kakaoLogout()
                            dataStoreUtility.apply {
                                context.setLoginState(false)
                            }
                            (context as? Activity)?.finish()
                        }
                        showDialog = false
                    }
                ) {
                    Text("확인" , color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("취소" , color = Color.Black)
                }
            },
            title = { Text("로그아웃" , color = Color.Black) },
            text = { Text("앱이 종료되는데 로그아웃 하시겠습니까?" , color = Color.Black) }
        )
    }


    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 15.dp, end = 15.dp),
        onClick = {
            showDialog = true
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
    ) {
        Text(
            text = "로그아웃하기",
            color = Color.Black
        )
    }
}




