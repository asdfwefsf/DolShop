package com.company.dolshop.screens.screentype.subscreen

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.presentation.R
import com.company.utility.encodeUrl
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen1(navController: NavController) {
    var okBoolean by remember { mutableStateOf(false) }
    val context = LocalContext.current


    val scope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState()


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
//                        if (okBoolean == true) {
                        navController.navigate(ScreenList.LoginScreen.route) {
                            popUpTo(ScreenList.LoginScreen.route) {
                                inclusive = true
                            }
                        }
//                        } else {
//                            Toast
//                                .makeText(context, "약관에 동의해주세요", Toast.LENGTH_SHORT)
//                                .show()
//                        }

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
                    .fillMaxWidth(0.33f)
                    .height(10.dp)
                    .background(Color(0xFFA8FF00))
            )
        }

        Text(
            "회원가입에 필요한",
            fontSize = 30.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        )
        Row {
            Text(
                "약관에 동의해주세요",
                fontSize = 30.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 20.dp, top = 5.dp)
            )
            Box(
                modifier = Modifier.background(Color.White)

            ) {
                Button(
                    modifier = Modifier.width(100.dp),
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                ) {
                    Text(text = "약관보기" , color = Color.Black)
                }
            }

        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {

            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContainerColor = Color.White,
                sheetContentColor = Color.Black,
                sheetContent = {
                    // 바텀 싯 내용
                    Column(
                        modifier = Modifier
                            .height(150.dp)
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {

                            if (okBoolean == false) {
                                Icon(
                                    painter = painterResource(id = R.drawable.signup1nochoice),
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        okBoolean = true
                                    }

                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.signup1yeschoice),
                                    contentDescription = "",
                                    modifier = Modifier.clickable {
                                        okBoolean = false
                                    }
                                )
                            }
                            Text("전체 동의하기")

                        }
                        Spacer(Modifier.size(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text("서비스 이용약관 (필수) ")
                            Spacer(modifier = Modifier.weight(1f))
                            Text("보기", modifier = Modifier.clickable {
                                val encodedUrl =
                                    encodeUrl("https://geonnuyasha.com/service")

                                navController.navigate("${ScreenList.PersonInfoWebView.route}/$encodedUrl")
                            })
                        }
                        Spacer(Modifier.size(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text("개인정보 처리방침 (필수) ")
                            Spacer(modifier = Modifier.weight(1f))
                            Text("보기", modifier = Modifier.clickable {
                                val encodedUrl =
                                    encodeUrl("https://geonnuyasha.com/personal")

                                navController.navigate("${ScreenList.PersonInfoWebView.route}/$encodedUrl")
                            })
                        }
                        Spacer(Modifier.size(10.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (okBoolean == true) {
                                    val falsedat = false.toString()
                                    navController.navigate("${ScreenList.SignUpScreen2.route}/$falsedat") {
                                        popUpTo(ScreenList.SignUpScreen2.route) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    Toast
                                        .makeText(context, "약관에 동의해주세요", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        ) {
                            Text(
                                text = "동의하고 시작하기"
                            )
                        }

                    }
                },
                sheetPeekHeight = 0.dp
            ) {
            }


        }

    }



}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}