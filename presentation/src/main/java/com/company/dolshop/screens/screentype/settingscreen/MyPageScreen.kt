package com.company.dolshop.screens.screentype.settingscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.company.dolshop.designsystem.DolShopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("설정")
                }
            )
        }
    ) { innerPadding ->
        SettingItemsList(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SettingItemsList(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        LeadSettingItem(icon = Icons.Default.Notifications, text = "알림설정")
        SettingItem(icon = Icons.Default.Notifications, text = "배송 알림")
        LastSettingItem(icon = Icons.Default.Notifications, text = "채팅 알림")
        // Add more SettingItem calls for "계정설정"
        LeadSettingItem(icon = Icons.Default.Person, text = "계정설정")
        SettingItem(icon = Icons.Default.Person, text = "개인정보")
        LastSettingItem(icon = Icons.Default.Person, text = "로그인 보안")
        // Add more SettingItem calls for "보안"
        LeadSettingItem(icon = Icons.Default.Person, text = "보안")
        SettingItem(icon = Icons.Default.Person, text = "비밀번호 변경")
        LastSettingItem(icon = Icons.Default.Person, text = "2단계 인증")
        // You can continue adding more items as needed
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
fun SettingItem(icon: ImageVector, text: String) {
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
        Icon(Icons.Default.ArrowForward, contentDescription = null)
    }
}

@Composable
fun LastSettingItem(icon: ImageVector, text: String) {
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
        Icon(Icons.Default.ArrowForward, contentDescription = null)
    }
    Divider() // 항목 사이에 구분선을 추가합니다.

}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    DolShopTheme {
        MyPageScreen()
    }
}