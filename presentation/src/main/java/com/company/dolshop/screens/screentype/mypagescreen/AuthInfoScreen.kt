package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthInfoScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val viewmodel: KakaoAuthiViewModel = hiltViewModel()
    val userInfoList = viewmodel.userInfoList.collectAsState().value
    Column {
//        Button(onClick = {
//            scope.launch {
//                viewmodel.kakaoLogout()
//            }
//        }) {
//            Text("logout")
//        }
//        Button(onClick = {
//            navController.navigate(ScreenList.MyPageScreen.route)
//        }) {
//            Text("마이 페이지")
//        }
        if (userInfoList != null) {
            Column {

                AsyncImage(model = userInfoList.authProfileImage, contentDescription = null)
                InfoItems("이름", userInfoList.authNicName)
                InfoItems("이메일 주소", userInfoList.authEmail)
                InfoItems("아이디 번호", userInfoList.authNumber)
                ClickableInfoItems("주소", userInfoList.authNicName)
                Text(
                    "주소 변경하기",
                    modifier = Modifier.clickable { Log.d("address", "address change") })

            }

//            Spacer(Modifier.size(8.dp))
//            Text(userInfoList.authEmail)
//            Spacer(Modifier.size(8.dp))
//            Text(userInfoList.authNicName)
//            Spacer(Modifier.size(8.dp))
//            Text(userInfoList.authNumber)
//            Spacer(Modifier.size(8.dp))
//            Text(userInfoList.authProfileImage)

        }
    }
}

@Composable
fun InfoItems(indextext: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(indextext, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text, fontWeight = FontWeight.Bold)

    }
    Divider()
}

@Composable
fun ClickableInfoItems(indextext: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(indextext, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text, fontWeight = FontWeight.Bold)

    }
    Divider()
}
