package com.company.dolshop.screens.screentype.productscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.company.designsystem.designsystem.component.OutlinedTextField.DefaultRowTwoOutLinedTextField
import com.company.designsystem.designsystem.component.dialog.BaseDialog
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.AddressViewModel
import com.company.dolshop.viewmodel.auth.AuthiViewModel
import com.company.domain.model.DomainAddress
import com.company.domain.model.DomainBaesongInfo
import com.company.domain.model.DomainProductModel
import com.company.presentation.R
import com.company.utility.decodeUrl
import com.company.utility.encodeUrl
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

@Composable
fun GuMaeScreen(gumaeProductModel: DomainProductModel, navController: NavController) {

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
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            BaesongzInputScreen(navController, baesongInfo, gumaeProductModel)
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaesongzInputScreen(
    navController: NavController,
    baesongInfo: DomainBaesongInfo,
    gumaeProductModel: DomainProductModel
) {
    val addressViewModel: AddressViewModel = hiltViewModel()
    val addressInfo = addressViewModel.addressList.collectAsState(
        initial = listOf(
            DomainAddress(
                0,
                " ",
                " ",
                " ",
                " ",
                " "
            )
        )
    ).value
    var selectedText by remember { mutableStateOf("0개") }

    var moveToAddressChangeDialogBoolean = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
        ) {
            AsyncImage(
                model = gumaeProductModel.image1,
                contentDescription = "",
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(Modifier.size(10.dp))
            Column {
                Row {
                    Text("상품명 : ", color = Color.Black)
                    Text(decodeUrl(gumaeProductModel.text1), color = Color.Black)
                }
                Spacer(Modifier.size(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    var expanded by remember { mutableStateOf(false) }
                    val items = listOf("1개", "2개", "3개", "4개", "5개", "6개", "7개", "8개", "9개", "10개")
                    Text("주문 갯수", color = Color.Black)
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedText,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                unfocusedIndicatorColor = Color.Black,
                                focusedIndicatorColor = Color.Green
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color.White),

                            ) {
                            items.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption, color = Color.Black) },
                                    onClick = {
                                        selectedText = selectionOption
                                        expanded = false
                                    },
                                )
                            }
                        }
                    }
                }

            }

        }

        if (addressInfo.isNotEmpty()) {
            DefaultRowTwoOutLinedTextField("우편번호", "우편번호", addressInfo[0].addressNumber)
            DefaultRowTwoOutLinedTextField("주소", "주소", addressInfo[0].address)
            DefaultRowTwoOutLinedTextField("상세주소", "상세주소", addressInfo[0].addressDetailName)
            DefaultRowTwoOutLinedTextField("전화번호", "전화번호", addressInfo[0].phoneNumber)
        }

        // 배송지 버튼 TODO 이 아래로 주소 정보 입력 하면 될 듯
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 15.dp, end = 15.dp),
            onClick = {
                moveToAddressChangeDialogBoolean.value = !moveToAddressChangeDialogBoolean.value
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
        ) {
            Text(
                text = "배송지 변경",
                color = Color.Black
            )
        }
        if (moveToAddressChangeDialogBoolean.value) {
            val realArgument = encodeUrl(Gson().toJson(gumaeProductModel))

            BaseDialog(
                moveToAddressChangeDialogBoolean,
                "배송지 변경",
                "배송지 변경하러 가시겠습니까?",
                { navController.navigate("${ScreenList.InputAddressInfoScreen.route}/${realArgument}") },
                {}
            )
        }


        var message by remember { mutableStateOf("") }
        var addressDialogBoolean by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.size(16.dp))
        if (message == "새로운 주소로 배송될 예정입니다.") {
            Button(onClick = {
                val realArgument = encodeUrl(Gson().toJson(gumaeProductModel))
                navController.navigate("${ScreenList.InputAddressInfoScreen.route}/${realArgument}")
            }) {
                Text("새로운 주소 입력하러sfsfsfsfsf 가기", color = Color.Black)
            }
        }
        Text(message, color = Color.Black)
        if(addressInfo.isNotEmpty()) {
            test(
                baesongInfo,
                selectedText,
                decodeUrl(gumaeProductModel.text1),
                decodeUrl(gumaeProductModel.image1),
                navController,
                addressInfo[0]
            )
        }

    }
}

@Composable
fun test(
    baesongInfo: DomainBaesongInfo,
    selectedText: String,
    productName: String,
    productLink: String,
    navController: NavController,
    domainAddress: DomainAddress
) {
    var selectedImageName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
    ) {
        val clickedIndication = remember { MutableInteractionSource() }
        // 은행3개 가로로
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val banks = listOf(
                "농협은행" to R.drawable.nonghub_bank,
                "기업은행" to R.drawable.giup_bank,
                "하나은행" to R.drawable.hana_bank,
            )
            banks.forEach { (name, image) ->
                val selectedBoolean = selectedImageName == name

                val borderWidth = if (selectedBoolean) 3.dp else 0.dp
                val borderColor = if (selectedBoolean) Color(0xFF7BF579) else Color.Black

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(
                            width = borderWidth,
                            color = borderColor,
                        )
                        .clickable(
                            interactionSource = remember { clickedIndication },
                            indication = null
                        ) {
                            selectedImageName = name
                            baesongInfo.bankName = name
                        }
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "",
                        modifier= Modifier.height(100.dp).width(100.dp)
                    )
                }

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val banks = listOf(
                "국민은행" to R.drawable.pepole_bank,
                "신한은행" to R.drawable.sinhan_bank,
                "우리은행" to R.drawable.woori_bank,
            )
            banks.forEach { (name, image) ->
                val selectedBoolean = selectedImageName == name

                val borderWidth = if (selectedBoolean) 3.dp else 0.dp
                val borderColor = if (selectedBoolean) Color(0xFF7BF579) else Color.Black

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .border(
                            width = borderWidth,
                            color = borderColor,
                        )
                        .clickable(
                            interactionSource = remember { clickedIndication },
                            indication = null
                        ) {
                            selectedImageName = name
                            baesongInfo.bankName = name
                        }
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "",
                        modifier= Modifier.height(100.dp).width(100.dp)
                    )
                }

            }
        }
        // 은행3개 가로로


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, bottom = 4.dp, end = 4.dp , top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("출금은행", color = Color.Black, modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = selectedImageName,
                onValueChange = {
                    selectedImageName = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .padding(start = 4.dp),
                placeholder = { Text("은행 사진을 클릭해주세요", color = Color.Black) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Green
                ),
                readOnly = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, bottom = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("계좌번호", color = Color.Black, modifier = Modifier.weight(1f))
            var bankNumber by remember { mutableStateOf("") }
            OutlinedTextField(
                value = bankNumber,
                onValueChange = {
                    bankNumber = it
                    baesongInfo.accountNumber = bankNumber
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .padding(start = 4.dp),
                placeholder = { Text("계좌번호를 입력해주세요", color = Color.Black) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Green
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, bottom = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("입금자명", color = Color.Black, modifier = Modifier.weight(1f))
            var accountPeopleName by remember { mutableStateOf("") }
            OutlinedTextField(
                placeholder = { Text("입금자명을 입력해주세요", color = Color.Black) },

                value = accountPeopleName,
                onValueChange = {
                    accountPeopleName = it

                    baesongInfo.accountOwnerName = accountPeopleName

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .padding(start = 4.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Green
                )
            )
        }

        var dialogBoolean = remember { mutableStateOf(false) }

//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 30.dp, start = 15.dp, end = 15.dp),
//            onClick = {
//                dialogBoolean.value = true
//            },
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
//        ) {
//            Text(
//                text = "주문하기",
//                color = Color.Black
//            )
//        }


        Jumun(baesongInfo, selectedText, productName, productLink , navController , domainAddress)

//        if (dialogBoolean.value == true) {
//            baesongConfirmDialog(baesongInfo, dialogBoolean, selectedText, productName, productLink)
//        }
    }


}

@Composable
private fun Jumun(
    baesongInfo: DomainBaesongInfo,
    selectedText: String,
    productName: String,
    productLink: String,
    navController: NavController,
    domainAddress : DomainAddress
    ) {
    val userViewModel: AuthiViewModel = hiltViewModel()
    val realtimeDB = Firebase.database
    val userInfoList = userViewModel.userInfoList.collectAsState().value
    val authNumber = userInfoList.authNumber
    val userRef = realtimeDB.getReference("users/$authNumber/baesongNaeYeock")

    //        "addressName" to baesongInfo.addressName,
    val baesongFirebase = mapOf(
        "addressNumber" to domainAddress.addressNumber,
        "address" to domainAddress.address,
        "addressDetailName" to domainAddress.addressDetailName,
        "phoneNumber" to domainAddress.phoneNumber,
        "bankName" to baesongInfo.bankName,
        "accountNumber" to baesongInfo.accountNumber,
        "accountOwnerName" to baesongInfo.accountOwnerName,
        // 배송 여부
        "baesongBoolean" to "false",
        "productName" to productName,
        "productGaeSu" to selectedText,
        "productURL" to productLink,
        // 배송 예정 시간
        "arrivedTime" to "ㅇ",
        // 위치 추적
        "location" to "ㅇ"
    )
    val context = LocalContext.current

    var dialogBoolean = remember { mutableStateOf(false) }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        onClick = {

            if (!checkAllJuMun(baesongFirebase)) {
                Log.d("sfwetlll", "빈 값이 있습니다.")
                Log.d("sfwetlll", baesongFirebase.toString())
                Toast.makeText(context, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()

            } else  {
                userRef.push().setValue(baesongFirebase)
                Toast.makeText(context, "주문 완료하였습니다.", Toast.LENGTH_SHORT).show()
                dialogBoolean.value = true

            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
    ) {
        Text("주문하기", color = Color.Black)
    }
    if(dialogBoolean.value) {
        BaseDialog(
            dialogBoolean,
            "주문완료",
            "주문 내역 확인하러 가시겠습니까?",
            {navController.navigate(ScreenList.GuMaeNaeYeokScreen.route)},
            {navController.navigate(ScreenList.RocksScreen.route)}
        )
    }

}

//@Composable
//fun baesongConfirmDialog(
//    baesongInfo: DomainBaesongInfo,
//    dialogBoolean: MutableState<Boolean>,
//    selectedText: String,
//    productName: String,
//    productLink: String
//) {
//    val realtimeDB = Firebase.database
//    val userViewModel: AuthiViewModel = hiltViewModel()
//    val userInfoList = userViewModel.userInfoList.collectAsState().value
//    val authNumber = userInfoList.authNumber
//
//    val userRef = realtimeDB.getReference("users/$authNumber/baesongNaeYeock")
//
//    Dialog(
//        onDismissRequest = { dialogBoolean.value = false },
//
//        ) {
//        Card(
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth()
//                .fillMaxHeight(0.7f),
//            elevation = CardDefaults.cardElevation(4.dp),
//            colors = CardDefaults.cardColors(Color.White)
//        ) {
//            Column(
//                modifier = Modifier.padding(4.dp)
//            ) {
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("손님 주소 별명", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.addressName, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("손님 우편번호", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.addressNumber, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("손님 주소", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.address, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("손님 상세 주소", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.addressDetailName, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("손님 전화번호", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.phoneNumber, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("은행이름", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.bankName, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("손님 계좌번호", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.accountNumber, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("손님 성함", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(baesongInfo.accountOwnerName, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("상품 이름", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(productName, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
////
////                Row(
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(horizontal = 8.dp)
////                ) {
////                    Text("상품 갯수", color = Color.Black)
////                    Spacer(modifier = Modifier.weight(1f))
////                    Text(selectedText, color = Color.Black)
////                }
////                Spacer(modifier = Modifier.height(4.dp))
//
//
////                val baesongFirebase = mapOf(
////                    "addressName" to baesongInfo.addressName,
////                    "addressNumber" to baesongInfo.addressNumber,
////                    "address" to baesongInfo.address,
////                    "addressDetailName" to baesongInfo.addressDetailName,
////                    "phoneNumber" to baesongInfo.phoneNumber,
////                    "bankName" to baesongInfo.bankName,
////                    "accountNumber" to baesongInfo.accountNumber,
////                    "accountOwnerName" to baesongInfo.accountOwnerName,
////                    "baesongBoolean" to "false",
////                    "productName" to productName,
////                    "productGaeSu" to selectedText,
////                    "productURL" to productLink,
////                    "arrivedTime" to ""
////                )
////                val context = LocalContext.current
////
////                Button(
////                    onClick = {
////
////                        if (!checkAllJuMun(baesongFirebase)) {
////                            Log.d("sfwetlll", "빈 값이 있습니다.")
////                            dialogBoolean.value = false
////                            Toast.makeText(context, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
////                        } else {
////                            userRef.push().setValue(baesongFirebase)
////                            dialogBoolean.value = false
////                        }
////                    }
////                ) {
////                    Text("주문 완료", color = Color.Black)
////                }
//            }
//
//        }
//    }
//}

fun checkAllJuMun(map: Map<String, String>): Boolean {
    return map.values.none { it.isEmpty() }
}