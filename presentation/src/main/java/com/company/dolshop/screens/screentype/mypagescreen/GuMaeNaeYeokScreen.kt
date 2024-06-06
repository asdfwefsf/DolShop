package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.company.designsystem.designsystem.component.OutlinedTextField.DefaultRowTwoOutLinedTextField
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.dolshop.screens.screentype.productscreen.checkAllJuMun
import com.company.dolshop.viewmodel.auth.AuthiViewModel
import com.company.domain.model.JuMunNaeYeockModel
import com.company.utility.decodeUrl
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun GuMaeNaeYeokScreen() {

    val userViewModel: AuthiViewModel = hiltViewModel()
    val userInfoNumber = userViewModel.userInfoList.collectAsState().value.authNumber

    val db = Firebase.database.reference
    val JuMunNaeYeock = db.child("users").child(userInfoNumber).child("baesongNaeYeock")

    var test by remember { mutableStateOf(listOf<JuMunNaeYeockModel>()) }

    JuMunNaeYeock.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val sfsdfsf = mutableListOf<JuMunNaeYeockModel>()

            snapshot.children.forEach { real ->
                val sefsaf = real.getValue(JuMunNaeYeockModel::class.java)
                if (sefsaf != null) {
                    sfsdfsf.add(sefsaf)
                }
                Log.d("sfsefis", "$sefsaf")
            }
            test = sfsdfsf
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })

    Log.d("sfaegawetasdfsf", "${test}")
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {


        if (test.isNotEmpty()) {
            items(test) { domainBaesongInfo ->

                DetailJuMunNaeYeok(domainBaesongInfo)
            }
        }



    }


}

@Composable
fun DetailJuMunNaeYeok(domainBaesongInfo: JuMunNaeYeockModel) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(start = 4.dp)
    ) {
        Row(
            modifier = Modifier.background(Color.White).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dol And Shop", color = Color.Black , modifier = Modifier.padding(top = 4.dp , bottom = 4.dp))
        }

        Text("상품 정보" , color = Color.Black)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp, top = 4.dp, bottom = 4.dp)
        ) {
            AsyncImage(
                model = domainBaesongInfo.productURL,
                contentDescription = "",
                modifier = Modifier.padding(start = 4.dp , top = 4.dp)
            )
        }
        Column {
            DefaultRowTwoOutLinedTextField("상품명" , "" , domainBaesongInfo.productName)
            DefaultRowTwoOutLinedTextField("주문 갯수" , "" , domainBaesongInfo.productGaeSu)
            DefaultRowTwoOutLinedTextField("상품명" , "" , decodeUrl(domainBaesongInfo.productName))
        }

        Text("배송지 정보" , color = Color.Black , modifier = Modifier.padding(top = 20.dp))
        DefaultRowTwoOutLinedTextField("주소" , "" , domainBaesongInfo.address)
        DefaultRowTwoOutLinedTextField("우편번호" , "" , domainBaesongInfo.addressNumber)
        DefaultRowTwoOutLinedTextField("상세주소" , "" , domainBaesongInfo.addressDetailName)
        DefaultRowTwoOutLinedTextField("전화번호" , "" , domainBaesongInfo.phoneNumber)
        if(domainBaesongInfo.arrivedTime != "ㅇ") {
            DefaultRowTwoOutLinedTextField("도착날짜" , "" , domainBaesongInfo.arrivedTime)
        }
        if(domainBaesongInfo.baesongBoolean != "false") {
            DefaultRowTwoOutLinedTextField("출발여부" , "" , domainBaesongInfo.baesongBoolean)
        }
        DefaultRowTwoOutLinedTextField("위치추적" , "" , domainBaesongInfo.location)


        var showDialog by remember { mutableStateOf(false) }
        val locations = splitLocations(domainBaesongInfo.location)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            onClick = {
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
        ) {
            Text("위치추적", color = Color.Black)
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "위치추적" , color = Color.Black)
                },
                text = {
                    Column {
                        Text("현재 위치가 빨간색으로 표시됩니다." , color = Color.Black)

                        locations.forEachIndexed { index, location ->
                            val displayLocation = location.replace("현재", "")

                            val textColor = if (location.contains("현재")) Color.Red else Color.Black
                            Text("배송지 위치 ${index + 1}: $displayLocation", color = textColor)
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("닫기" , color = Color.Black)
                    }
                },
                containerColor = Color.White
            )
        }


        Text("결제 정보" , color = Color.Black , modifier = Modifier.padding(top = 20.dp))
        DefaultRowTwoOutLinedTextField("은행이름" , "" , domainBaesongInfo.bankName)
        DefaultRowTwoOutLinedTextField("계좌번호" , "" , domainBaesongInfo.accountNumber)
        DefaultRowTwoOutLinedTextField("입금자명" , "" , domainBaesongInfo.accountOwnerName)
        Spacer(Modifier.size(4.dp))


    }
}

fun splitLocations(location: String): List<String> {
    return location.split(":")
}





