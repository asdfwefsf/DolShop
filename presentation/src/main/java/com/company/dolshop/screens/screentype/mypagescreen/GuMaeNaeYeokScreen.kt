package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.company.dolshop.viewmodel.AuthiViewModel
import com.company.domain.model.JuMunNaeYeockModel
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
    LazyColumn {


        if (test.isNotEmpty()) {
            items(test) { domainBaesongInfo ->

                DetailJuMunNaeYeok(domainBaesongInfo)
            }
        }



    }


}

@Composable
fun DetailJuMunNaeYeok(domainBaesongInfo: JuMunNaeYeockModel) {
    Column {
        Text("Address Name: ${domainBaesongInfo.addressName}")
        Text("Address Number: ${domainBaesongInfo.addressNumber}")
        Text("Address: ${domainBaesongInfo.address}")
        Text("Address Detail Name: ${domainBaesongInfo.addressDetailName}")
        Text("휴대폰 번호: ${domainBaesongInfo.phoneNumber}")
        Text("은행 이름: ${domainBaesongInfo.bankName}")
        Text("입금자 계좌번호: ${domainBaesongInfo.accountNumber}")
        Text("입금자명: ${domainBaesongInfo.accountOwnerName}")
        Text("baesongBoolean: ${domainBaesongInfo.baesongBoolean}")
        Text("상품갯수: ${domainBaesongInfo.productGaeSu}")
        Text("상품명: ${domainBaesongInfo.productName}")
        Text("도착 예정 날짜 : ${domainBaesongInfo.arrivedTime}")
        AsyncImage(model = domainBaesongInfo.productURL, contentDescription = "")
    }
}
