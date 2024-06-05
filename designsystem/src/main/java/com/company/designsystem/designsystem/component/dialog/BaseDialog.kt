package com.company.designsystem.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BaseDialog(
    showDialog : MutableState<Boolean>,
    title : String,
    contentText : String,
    confirmListener : () -> Unit,
    dismissButtonListener : () -> Unit
    ) {
    AlertDialog(
        containerColor = Color.White,
        modifier = Modifier.background(Color.White),
        onDismissRequest = { showDialog.value = false },
        confirmButton = {
            Button(
                onClick = {
                    confirmListener()
                    showDialog.value = false
                }
            ) {
                Text("확인" , color = Color.Black)
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    showDialog.value = false
                    dismissButtonListener()
                }
            ) {
                Text("취소" , color = Color.Black)
            }
        },
        title = { Text(text = title , color = Color.Black) },
        text = { Text(text = contentText , color = Color.Black) }
    )
}
















































