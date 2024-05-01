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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.KakaoAuthiViewModel

@Composable
fun AuthInfoScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val viewmodel: KakaoAuthiViewModel = hiltViewModel()
    val userInfoList = viewmodel.userInfoList.collectAsState().value
    var detailAddress by remember { mutableStateOf(" ") }
    Column {
        if (userInfoList != null) {
            Column {
                Spacer(modifier = Modifier.padding(top = 8.dp))
                AsyncImage(model = userInfoList.authProfileImage, contentDescription = null,
                    modifier = Modifier.padding(start = 16.dp))
                TopInfoItems("이름", userInfoList.authNicName)
                InfoItems("이메일 주소", userInfoList.authEmail)
                InfoItems("아이디 번호", userInfoList.authNumber)
                LeadingIconInfoItems(navController , "배송지 정보" , Icons.Default.KeyboardArrowRight, "입력하기" , ScreenList.InputAddressInfoScreen.route)
                InfoItems("전화번호", "")
                InfoItems("배송지 주소", "")
                Row {
                    InfoItems("상세주소", "")
                }
            }
        }

    }
}

@Composable
fun TopInfoItems(indextext: String, text: String) {
    Divider()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(indextext, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text, fontWeight = FontWeight.Bold)

    }
    Divider()
}
@Composable
fun InfoItems(indextext: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
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
        Text(indextext, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text, fontWeight = FontWeight.Bold)

    }
    Divider()
}

@Composable
fun LeadingIconInfoItems(navController : NavController , defaultText: String , icon : ImageVector, address: String , route : String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(defaultText, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(address, fontWeight = FontWeight.Bold)
        Icon(icon, contentDescription = null, modifier = Modifier
            .size(24.dp)
            .clickable {
                navController.navigate(route) {
                    launchSingleTop = true
                }
            })

    }
    Divider()

}
