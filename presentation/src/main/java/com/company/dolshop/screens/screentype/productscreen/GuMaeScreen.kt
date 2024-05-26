package com.company.dolshop.screens.screentype.productscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.AddressViewModel
import com.company.domain.model.DomainProductModel
import com.company.domain.model.GumaeProductModel
import com.company.presentation.R
import kotlinx.coroutines.launch

@Composable
fun GuMaeScreen(gumaeProductModel: String, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            BaesongzInputScreen(navController)
        }
        item {
            test()
        }

    }

}

@Composable
fun BaesongzInputScreen(navController: NavController) {
    val addressViewModel: AddressViewModel = hiltViewModel()
    val addressInfo = addressViewModel.addressList.collectAsState().value
    val scope = rememberCoroutineScope()

    // 애니메이션 보이는거 불리안 값
    var isAddressVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("배송지")
        Text("기본 배송지", modifier = Modifier.clickable {
            isAddressVisible = !isAddressVisible
        })

        var message by remember { mutableStateOf("") }

        AnimatedVisibility(
            visible = isAddressVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
        ) {
            if (addressInfo.isNotEmpty()) {
                Column {
                    Text(addressInfo[0].addressName)
                    Text(addressInfo[0].address)
                    Text(addressInfo[0].addressNumber)
                    Text(addressInfo[0].addressDetailName)
                    Text(addressInfo[0].phoneNumber)
                    Text("이대로 배송하시겠습니까 ? ")

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {

                                message = "위의 주소로 배송될 예정입니다."

                            },
                            modifier = Modifier.weight(1f)

                        ) {
                            Text("네")
                        }
                        Button(
                            onClick = {
                                message = "새로운 주소로 배송될 예정입니다."

                            },
                            modifier = Modifier.weight(1f)

                        ) {
                            Text("아니오")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(16.dp))
        Text(message)
//        AnimatedVisibility(
//            visible = isNoVisible,
//            enter = fadeIn(animationSpec = animationSockDo),
//            exit = fadeOut(animationSpec = animationSockDo)
//        ) {
//            Text("새로운 주소로 배송될 예정입니다.")
//        }
//        AnimatedVisibility(
//            visible = isYesVisible,
//            enter = fadeIn(animationSpec = animationSockDo),
//            exit = fadeOut(animationSpec = animationSockDo)
//        ) {
//            Text("위의 주소로 배송될 예정입니다.")
//        }


    }

}

@Composable
fun test() {
    var selectedImageName by remember { mutableStateOf("") }



    Column {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.nonghub_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { selectedImageName = "농협은행" }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.giup_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { selectedImageName = "기업은행" }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.hana_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { selectedImageName = "하나은행" }
            )
        }
        Spacer(Modifier.size(10.dp))
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pepole_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { selectedImageName = "국민은행" }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.sinhan_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { selectedImageName = "신한은행" }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.woori_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { selectedImageName = "우리은행" }
            )
            Spacer(Modifier.size(10.dp))
        }

        Row(modifier = Modifier.fillMaxSize()) {
            Text("출금 은행 명")
            Spacer(Modifier.size(50.dp))
            BankInfo(selectedImageName)
        }

        Row(modifier = Modifier.fillMaxSize()) {
            Text("계좌번호")
            Spacer(Modifier.size(50.dp))
            BankNumberInfo()
        }

        Row(modifier = Modifier.fillMaxSize()) {
            Text("입금자명")
            Spacer(Modifier.size(50.dp))
            AccountPeopleName()
        }

    }



}

@Composable
fun BankInfo(selectedImageName : String) {
    Text(selectedImageName)
}

@Composable
fun BankNumberInfo() {

    var bankNumber by remember { mutableStateOf("") }
    TextField(value = bankNumber, onValueChange = {bankNumber = it})

}

@Composable
fun AccountPeopleName() {

    var accountPeopleName by remember { mutableStateOf("") }
    TextField(value = accountPeopleName, onValueChange = {accountPeopleName = it})

}

















@Preview
@Composable
fun TestPreview() {
    test()
}