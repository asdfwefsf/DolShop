package com.company.dolshop.screens.screentype.mypagescreen

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.AuthiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen(navController: NavController) {
    val viewmodel : AuthiViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(viewmodel.userInfoList.collectAsState().value.authNicName)
                }
            )
        }
    ) { innerPadding ->
        SettingItemsList(
            modifier = Modifier.padding(innerPadding),
            navController
        )
    }
}

@Composable
fun SettingItemsList(modifier: Modifier = Modifier , navController: NavController) {
    Column(modifier = modifier) {
//        LeadSettingItem(icon = Icons.Default.Notifications, text = "알림설정")
//        SettingItem(icon = Icons.Default.Notifications, text = "배송 알림" , "" , navController)
//        LastSettingItem(icon = Icons.Default.Notifications, text = "채팅 알림" , "" , navController)

        LeadSettingItem(icon = Icons.Default.Person, text = "계정설정")
        SettingItem(icon = Icons.Default.Person, text = "개인정보" , ScreenList.AuthInfoScreen.route , navController)
        LastSettingItem(icon = Icons.Default.Person, text = "로그아웃" , ScreenList.LogoutScreen.route , navController)

        LeadSettingItem(icon = Icons.Default.Person, text = "쿠폰")
//        SettingItem(icon = Icons.Default.Person, text = "내 쿠폰" , "" , navController)
        LastSettingItem(icon = Icons.Default.Person, text = "내 쿠폰" , ScreenList.MyCouponScreen.route , navController)
//
        LeadSettingItem(icon = Icons.Default.Person, text = "저장일기")
        LastSettingItem(icon = Icons.Default.Person, text = "저장일기 보러가기" , "저장일기" , navController)

        LeadSettingItem(icon = Icons.Default.Person, text = "주문내역")
        LastSettingItem(icon = Icons.Default.Person, text = "주문내역 보러가기" , "주문내역" , navController)
    }
}

@Composable
fun LeadSettingItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontWeight = FontWeight.Bold)

    }
}

@Composable
fun SettingItem(icon: ImageVector, text: String , route : String , navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.ArrowForward, contentDescription = null , modifier = Modifier.clickable {
            navController.navigate(route)
        })
    }
}

@Composable
fun LastSettingItem(icon: ImageVector, text: String , route : String , navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.ArrowForward, contentDescription = null , modifier = Modifier.clickable {
            navController.navigate(route)
        })
    }
    Divider()

}
