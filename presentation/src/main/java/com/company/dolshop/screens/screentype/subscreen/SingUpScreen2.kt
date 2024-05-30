package com.company.dolshop.screens.screentype.subscreen

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import org.w3c.dom.Text

@Composable
fun SingUpScreen2(navController: NavController) {

    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var kakaoEmail by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
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

        Column (
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
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
                "아래 내용을 작성해주세요",
                fontSize = 30.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text("이름" , color = Color.Black)
            Spacer(Modifier.size(5.dp))
            OutlinedTextField(value = name, onValueChange = { name = it } , modifier = Modifier.fillMaxWidth() , colors = TextFieldDefaults.colors(focusedContainerColor = Color.White , unfocusedContainerColor = Color.White , unfocusedIndicatorColor  = Color.Black , focusedIndicatorColor = Color.Green))

            Text("아이디" , color = Color.Black)
            Spacer(Modifier.size(5.dp))
            OutlinedTextField(value = id, onValueChange = { id = it } , modifier = Modifier.fillMaxWidth() , colors = TextFieldDefaults.colors(focusedContainerColor = Color.White , unfocusedContainerColor = Color.White, unfocusedIndicatorColor  = Color.Black , focusedIndicatorColor = Color.Green))

            Text("비밀번호" , color = Color.Black)
            Spacer(Modifier.size(5.dp))
            OutlinedTextField(value = password, onValueChange = { password = it } , modifier = Modifier.fillMaxWidth() , colors = TextFieldDefaults.colors(focusedContainerColor = Color.White , unfocusedContainerColor = Color.White, unfocusedIndicatorColor  = Color.Black , focusedIndicatorColor = Color.Green))

            Text("이메일" , color = Color.Black)
            Spacer(Modifier.size(5.dp))
            OutlinedTextField(value = kakaoEmail, onValueChange = { kakaoEmail = it } , modifier = Modifier.fillMaxWidth() , colors = TextFieldDefaults.colors(focusedContainerColor = Color.White , unfocusedContainerColor = Color.White, unfocusedIndicatorColor  = Color.Black , focusedIndicatorColor = Color.Green))

            Text("전화번호" , color = Color.Black)
            Spacer(Modifier.size(5.dp))
            OutlinedTextField(value = phoneNumber, onValueChange = { phoneNumber = it } , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) , modifier = Modifier.fillMaxWidth() , colors = TextFieldDefaults.colors(focusedContainerColor = Color.White , unfocusedContainerColor = Color.White, unfocusedIndicatorColor  = Color.Black , focusedIndicatorColor = Color.Green))

            Spacer(Modifier.size(250.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    onClick = {
                        if(id.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && kakaoEmail.isNotEmpty()) {
                            navController.navigate(ScreenList.SignUpScreen3.route) {
                                popUpTo(ScreenList.SignUpScreen3.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(context, "빈칸을 모두 채워주세요", Toast.LENGTH_SHORT).show()
                        }

                    }
                ) {
                    Text( text = "시작하기" )
                }

        }



//        Text(
//            text = "SingUpScreen2",
//            modifier = Modifier.clickable {
//
//            }
//        )
    }
}
//
//
//fun TextField.et() {
//
//}
