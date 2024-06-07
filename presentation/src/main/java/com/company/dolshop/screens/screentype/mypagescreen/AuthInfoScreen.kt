package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.AddressViewModel
import com.company.dolshop.viewmodel.auth.AuthiViewModel
import com.company.domain.model.DomainProductModel
import com.company.utility.urlmapper.encodeUrl
import com.google.gson.Gson

@Composable
fun AuthInfoScreen(navController: NavController) {

    val authiViewModel: AuthiViewModel = hiltViewModel()
    val userInfoList = authiViewModel.userInfoList.collectAsState().value


    val addressViewModel : AddressViewModel = hiltViewModel()
    val addressList = addressViewModel.addressList.collectAsState().value

    Log.d("sflsejfisfen" , userInfoList.toString())
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        if (userInfoList != null) {
            Column {
                Spacer(modifier = Modifier.padding(top = 8.dp))
                if(userInfoList.authProfileImage.isNotEmpty()) {
                    AsyncImage(model = userInfoList.authProfileImage, contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp))
                } else {
                    AsyncImage(model = "https://t1.kakaocdn.net/account_images/default_profile.jpeg.twg.thumb.R110x110", contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp))
                }

                TopInfoItems("이름", userInfoList.authNickName)
                InfoItems("이메일 주소", userInfoList.authEmail)
                InfoItems("아이디 번호", userInfoList.authNumber)
                LeadingIconInfoItemsToInputAddressInfo(navController , "배송지 정보" , Icons.Default.KeyboardArrowRight, "입력하기" , ScreenList.InputAddressInfoScreen.route)

                if(addressList.isNotEmpty()) {
                    InfoItems("전화번호", addressList[0].phoneNumber)
                    InfoItems("우편번호", addressList[0].addressNumber)
                    InfoItems("배송지 주소", addressList[0].address)
                    Row {
                        InfoItems("상세주소", addressList[0].addressDetailName)
                    }
                } else {
                    InfoItems("전화번호", "")
                    InfoItems("우편번호", "")
                    InfoItems("배송지 주소", "")
                    Row {
                        InfoItems("상세주소", "")
                    }
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
//@Composable
//fun InfoItems(indextext: String, text: String) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(indextext, fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.weight(1f))
//        Text(text, fontWeight = FontWeight.Bold)
//
//
//    }
//    Divider()
//}
@Composable
fun InfoItems(indextext: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(indextext, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
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


@Composable
fun LeadingIconInfoItemsToInputAddressInfo(navController : NavController , defaultText: String , icon : ImageVector, address: String , route : String) {
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
                val realArgument = encodeUrl(Gson().toJson(DomainProductModel(
                    "", "" , "" , "" , "" , "", "", "", "" , ""
                )))

                navController.navigate("${route}/${realArgument}") {
                    launchSingleTop = true
                }
            })

    }
    Divider()

}
