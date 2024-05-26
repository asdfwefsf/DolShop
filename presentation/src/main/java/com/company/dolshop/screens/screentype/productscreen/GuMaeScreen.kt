package com.company.dolshop.screens.screentype.productscreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.dolshop.viewmodel.AddressViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.domain.model.DomainBaesongInfo
import com.company.presentation.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun GuMaeScreen(gumaeProductModel: String, navController: NavController) {

    var baesongInfo = DomainBaesongInfo(
        addressName = "",
        addressNumber = "",
        address = "",
        addressDetailName = "",
        phoneNumber = "",
        bankName = "",
        accountNumber = "",
        accountOwnerName = ""
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            BaesongzInputScreen(navController, baesongInfo)
        }
    }


}

@Composable
fun BaesongzInputScreen(navController: NavController, baesongInfo: DomainBaesongInfo) {
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
                                baesongInfo.addressName = addressInfo[0].addressName
                                baesongInfo.address = addressInfo[0].address
                                baesongInfo.addressNumber = addressInfo[0].addressNumber
                                baesongInfo.addressDetailName = addressInfo[0].addressDetailName
                                baesongInfo.phoneNumber = addressInfo[0].phoneNumber
                                Log.d("sfsfs", baesongInfo.toString())
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

        test(baesongInfo)
    }
}

@Composable
fun test(baesongInfo: DomainBaesongInfo) {
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
                    .clickable {
                        selectedImageName = "농협은행"
                        baesongInfo.bankName = "농협은행"
                    }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.giup_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        selectedImageName = "기업은행"
                        baesongInfo.bankName = "기업은행"

                    }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.hana_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        selectedImageName = "하나은행"
                        baesongInfo.bankName = "하나은행"

                    }
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
                    .clickable {
                        selectedImageName = "국민은행"
                        baesongInfo.bankName = "국민은행"

                    }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.sinhan_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        selectedImageName = "신한은행"
                        baesongInfo.bankName = "신한은행"

                    }
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.woori_bank),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        selectedImageName = "우리은행"
                        baesongInfo.bankName = "우리은행"

                    }
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
            BankNumberInfo(baesongInfo)
        }

        Row(modifier = Modifier.fillMaxSize()) {
            Text("입금자명")
            Spacer(Modifier.size(50.dp))
            AccountPeopleName(baesongInfo)
        }

        var dialogBoolean = remember { mutableStateOf(false) }
        Button(onClick = {
            dialogBoolean.value = true
        }) {
            Text("확인")
        }
        if (dialogBoolean.value == true) {
            baesongConfirmDialog(baesongInfo, dialogBoolean)
        }
    }


}

@Composable
fun BankInfo(selectedImageName: String) {
    Text(selectedImageName)
}

@Composable
fun BankNumberInfo(baesongInfo: DomainBaesongInfo) {

    var bankNumber by remember { mutableStateOf("") }
    TextField(value = bankNumber, onValueChange = {
        bankNumber = it

        baesongInfo.accountNumber = bankNumber
    })


}

@Composable
fun AccountPeopleName(baesongInfo: DomainBaesongInfo) {

    var accountPeopleName by remember { mutableStateOf("") }
    TextField(value = accountPeopleName, onValueChange = {
        accountPeopleName = it

        baesongInfo.accountOwnerName = accountPeopleName


    })

}

@Composable
fun baesongConfirmDialog(baesongInfo: DomainBaesongInfo, dialogBoolean: MutableState<Boolean>) {
    val realtimeDB = Firebase.database
    val userViewModel: KakaoAuthiViewModel = hiltViewModel()
    val userInfoList = userViewModel.userInfoList.collectAsState().value
    val authNumber = userInfoList.authNumber

    val userRef = realtimeDB.getReference("users/$authNumber/baesongNaeYeock")

    Dialog(
        onDismissRequest = { dialogBoolean.value = false },

        ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("손님 주소 별명")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.addressName)
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("손님 우편번호")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.addressNumber)
                }
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("손님 주소")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.address)

                }
                Spacer(modifier = Modifier.height(4.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("손님 상세 주소")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.addressDetailName)

                }
                Spacer(modifier = Modifier.height(4.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("손님 전화번호")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.phoneNumber)

                }
                Spacer(modifier = Modifier.height(4.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("은행이름")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.bankName)

                }
                Spacer(modifier = Modifier.height(4.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("손님 계좌번호")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.accountNumber)

                }
                Spacer(modifier = Modifier.height(4.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text("손님 성함")
                    Spacer(modifier = Modifier.weight(1f))

                    Text(baesongInfo.accountOwnerName)

                }
                Spacer(modifier = Modifier.height(4.dp))

                val baesongFirebase = mapOf(
                    "addressName" to baesongInfo.addressName,
                    "addressNumber" to baesongInfo.addressNumber,
                    "address" to baesongInfo.address,
                    "addressDetailName" to baesongInfo.addressDetailName,
                    "phoneNumber" to baesongInfo.phoneNumber,
                    "bankName" to baesongInfo.bankName,
                    "accountNumber" to baesongInfo.accountNumber,
                    "accountOwnerName" to baesongInfo.accountOwnerName
                )
                Button(
                    onClick = {
                        userRef.push().setValue(baesongFirebase)
                        dialogBoolean.value = false
                    }
                ) {
                    Text("주문 완료")

                }
            }

        }
    }
}

