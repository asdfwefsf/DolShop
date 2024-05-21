package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.ui.theme.DolShopTheme
import com.company.dolshop.viewmodel.AddressViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.domain.model.DomainAddress
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
@ExperimentalMaterial3Api
fun InputAddressInfoScreen(navController: NavController) {
    val kakaoAuthiViewModel: KakaoAuthiViewModel = hiltViewModel()

    val scope = rememberCoroutineScope()
    val realtimeDB = Firebase.database
    val authNumber = kakaoAuthiViewModel.userInfoList.collectAsState().value.authNumber

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val addressViewModel: AddressViewModel = hiltViewModel()

    // test
    var addressNumber: String? by remember { mutableStateOf("") }
    var address: String? by remember { mutableStateOf("") }
    var changeResult = savedStateHandle?.get<Boolean>("change") ?: false

    val addressName by addressViewModel.addressName.collectAsState()
//    var addressNumber = addressViewModel.addressNumber.collectAsState().value
//    var address = addressViewModel.address.collectAsState().value
    var detailedAddress = addressViewModel.detailedAddress.collectAsState().value
    var phoneNumber = addressViewModel.phoneNumber.collectAsState().value

    LaunchedEffect(key1 = savedStateHandle) {
        addressNumber = savedStateHandle?.get<String>("addressNumber") ?: ""
        address = savedStateHandle?.get<String>("address") ?: ""
        Log.d("jisung", addressNumber + address)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("배송지 관련 정보 추가")

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = addressName,
            onValueChange = {
                addressViewModel.setAddressName(it)
            },
            label = { Text("배송지명 (선택)") },
            modifier = Modifier.fillMaxWidth()
        )

        // 우편번호
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            OutlinedTextField(
                value = addressNumber.toString(),
                onValueChange = {
                    addressViewModel.setAddressNumber(it)
                },
                label = { Text("우편번호") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { navController.navigate(ScreenList.AddressScreen.route) },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("조회")
            }
        }

        // 주소
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = address.toString(),
            onValueChange = {
                addressViewModel.setAddress(it)
            },
            label = { Text("주소") },
            modifier = Modifier.fillMaxWidth()
        )

        // 상세주소
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = detailedAddress,
            onValueChange = {
                addressViewModel.setDetailAddressName(it)
            },
            label = { Text("상세주소") },
            modifier = Modifier.fillMaxWidth()
        )

        // 전화번호
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                addressViewModel.setPhoneNumber(it)
            }, label = { Text("전화번호") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        // 저장버튼
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                addressViewModel.saveAddress(
                    DomainAddress(
                        addressName = addressName,
                        addressNumber = addressNumber.toString(),
                        address = address.toString(),
                        addressDetailName = detailedAddress,
                        phoneNumber = phoneNumber
                    )
                )
                navController.navigate(ScreenList.AuthInfoScreen.route) {
                    launchSingleTop = true
                    popUpTo(ScreenList.AuthInfoScreen.route) {
                        inclusive = true
                    }

                }

                val userRef = realtimeDB.getReference("users/$authNumber/address")
                val userData = mapOf(
                    "addressName" to addressName,
                    "addressNumber" to addressNumber,
                    "address" to address,
                    "detailedAddress" to detailedAddress,
                    "phoneNumber" to phoneNumber
                )
                userRef.setValue(userData)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("저장")
        }
    }

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun InputAddressInfoScreenPreview() {
//    DolShopTheme {
//        InputAddressInfoScreen()
//    }
//}