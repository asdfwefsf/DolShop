package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.domain.model.DomainBaesongInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun GuMaeNaeYeokScreen() {

    val userViewModel: KakaoAuthiViewModel = hiltViewModel()
    val userInfoNumber = userViewModel.userInfoList.collectAsState().value.authNumber

    val db = Firebase.database.reference
    val JuMunNaeYeock = db.child("users").child(userInfoNumber).child("baesongNaeYeock")

    var test by remember { mutableStateOf(listOf<DomainBaesongInfo>()) }

    JuMunNaeYeock.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val sfsdfsf = mutableListOf<DomainBaesongInfo>()

            snapshot.children.forEach { real ->
                val sefsaf = real.getValue(DomainBaesongInfo::class.java)
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

    Column {
        Text("GuMaeNaeYeokScreen")
        test.forEach { domainBaesongInfo ->
            DetailJuMunNaeYeok(domainBaesongInfo)
        }
    }


}

@Composable
fun DetailJuMunNaeYeok(domainBaesongInfo: DomainBaesongInfo) {
    Column {
        Text("Address Name: ${domainBaesongInfo.addressName}")
        Text("Address Number: ${domainBaesongInfo.addressNumber}")
        Text("Address: ${domainBaesongInfo.address}")
        Text("Address Detail Name: ${domainBaesongInfo.addressDetailName}")
        Text("Phone Number: ${domainBaesongInfo.phoneNumber}")
        Text("Bank Name: ${domainBaesongInfo.bankName}")
        Text("Account Number: ${domainBaesongInfo.accountNumber}")
        Text("Account Owner Name: ${domainBaesongInfo.accountOwnerName}")
    }
}
