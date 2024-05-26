package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.domain.model.DomainBaesongInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun GuMaeNaeYeokScreen() {

    val userViewModel : KakaoAuthiViewModel = hiltViewModel()
    val userInfoNumber = userViewModel.userInfoList.collectAsState().value.authNumber

    val db = Firebase.database.reference
    val JuMunNaeYeock = db.child("users").child(userInfoNumber).child("baesongNaeYeock")

    JuMunNaeYeock.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach { real ->
                val test = real.getValue(DomainBaesongInfo::class.java)
                Log.d("sfsefis" , "${test}")
            }

        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })





    Text("GuMaeNaeYeokScreen")



}