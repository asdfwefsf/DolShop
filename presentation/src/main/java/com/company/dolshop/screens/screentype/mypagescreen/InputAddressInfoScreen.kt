package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.AddressViewModel
import com.company.dolshop.viewmodel.auth.AuthiViewModel
import com.company.domain.model.DomainAddress
import com.company.domain.model.DomainProductModel
import com.company.utility.encodeUrl
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

@Composable
@ExperimentalMaterial3Api
fun InputAddressInfoScreen(navController: NavController, gumaeDolInfo: DomainProductModel) {
    val authiViewModel: AuthiViewModel = hiltViewModel()

    val realtimeDB = Firebase.database
    val authNumber = authiViewModel.userInfoList.collectAsState().value.authNumber

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val addressViewModel: AddressViewModel = hiltViewModel()

    // test
    var addressNumber: String? by remember { mutableStateOf("") }
    var address: String? by remember { mutableStateOf("") }
    var HARD: DomainProductModel? by remember {
        mutableStateOf(
            DomainProductModel(
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            )
        )
    }
    var changeResult = savedStateHandle?.get<Boolean>("change") ?: false

    val addressName by addressViewModel.addressName.collectAsState()
    var detailedAddress = addressViewModel.detailedAddress.collectAsState().value
    var phoneNumber = addressViewModel.phoneNumber.collectAsState().value

    val fsfsef = navController.previousBackStackEntry?.destination



    Log.d("sfsesvinoesfnof", fsfsef?.route!!)
    LaunchedEffect(key1 = savedStateHandle) {
        addressNumber = savedStateHandle?.get<String>("addressNumber") ?: ""
        address = savedStateHandle?.get<String>("address") ?: ""
        HARD = savedStateHandle?.get<DomainProductModel>("HARD")


        Log.d("jisung", addressNumber + address)
    }


//    if (fsfsef?.route == "개인정보") {
//        var gumaeDolInfo = DomainProductModel("", "", "", "", "", "", "", "", "", "")
//        val encodedProductInfo =
//            encodeUrl(Gson().toJson(gumaeDolInfo, DomainProductModel::class.java))
//    } else {
//        val sssss = gumaeDolInfo
//    }

    // 상품 정보
//    val productInfo = gumaeDolInfo?.let { Gson().fromJson(it, DomainProductModel::class.java) }
//    val productInfo = decodeUrl(gumaeDolInfo!!)
    // 상품 정보

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val context = LocalContext.current
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

            if (fsfsef?.route == "개인정보") {
                val gibon = DomainProductModel("", "", "", "", "", "", "", "", "", "")
                val encodedgibon =
                    encodeUrl(Gson().toJson(gibon, DomainProductModel::class.java))
                Button(
                    onClick = { navController.navigate("${ScreenList.AddressScreen.route}/${encodedgibon}") },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("기본꼴 조회")
                }
            } else {
                val encodedgumaeDolInfo =
                    encodeUrl(Gson().toJson(gumaeDolInfo, DomainProductModel::class.java))
                Button(
                    onClick = { navController.navigate("${ScreenList.AddressScreen.route}/${encodedgumaeDolInfo}") },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("변형꼴 조회")
                }
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

                if (addressName.isNotEmpty() &&
                    addressNumber.toString().isNotEmpty() &&
                    address.toString().isNotEmpty() &&
                    detailedAddress.isNotEmpty() &&
                    phoneNumber.isNotEmpty()
                ) {
                    addressViewModel.saveAddress(
                        DomainAddress(
                            addressName = addressName,
                            addressNumber = addressNumber.toString(),
                            address = address.toString(),
                            addressDetailName = detailedAddress,
                            phoneNumber = phoneNumber
                        )
                    )


                    if (fsfsef?.route == "개인정보") {
                        navController.navigate(ScreenList.AuthInfoScreen.route) {
                            launchSingleTop = true
                            popUpTo(ScreenList.AuthInfoScreen.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        val encodedProductInfo =
                            encodeUrl(Gson().toJson(gumaeDolInfo, DomainProductModel::class.java))
                        navController.navigate("${ScreenList.GuMaeScreen.route}/${encodedProductInfo}") {
                            launchSingleTop = true
                            popUpTo(ScreenList.GuMaeScreen.route) {
                                inclusive = true
                            }
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
                } else {
                    Toast.makeText(context , "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                }


            },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (fsfsef?.route == "개인정보") {
                Text("저장하기")
            }
//            else if (fsfsef?.route == "") {
//                Text("저장하기")
//            }
            else {
                Text(text = "새로운 주소 저장하기")
            }
        }
    }
}

