package com.company.dolshop.screens.screentype.mypagescreen

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.setLoginState
import kotlinx.coroutines.launch

@Composable
fun LogoutScreen() {
    val scope = rememberCoroutineScope()
    val viewmodel : KakaoAuthiViewModel = hiltViewModel()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val dataStoreUtility = DataStoreUtility.getInstance()

    if (showDialog) {
        AlertDialog(
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
                    Text("확인")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("취소")
                }
            },
            title = { Text("로그아웃") },
            text = { Text("앱이 종료되는데 로그아웃 하시겠습니까?") }
        )
    }


    Column {
        Button(onClick = { showDialog = true }) {
            Text("로그아웃")
        }
    }
}


