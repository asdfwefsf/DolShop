package com.company.dolshop.screens.screentype.productscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun GuMaeScreen(gumaeProductModel : String , navController: NavController) {
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
    val addressViewModel : AddressViewModel = hiltViewModel()
    val addressInfo = addressViewModel.addressList.collectAsState().value
    val scope = rememberCoroutineScope()
    var isAddressVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("배송지")
        Text("기본 배송지" , modifier = Modifier.clickable {
            isAddressVisible = !isAddressVisible

//            navController.navigate (
//
//                ScreenList.InputAddressInfoScreen.route
//
//            )
        })
        AnimatedVisibility(
            visible = isAddressVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            if (addressInfo.isNotEmpty()) {
                Column {
                    Text(addressInfo[0].addressName)
                    Text(addressInfo[0].address)
                    Text(addressInfo[0].addressNumber)
                    Text(addressInfo[0].addressDetailName)
                    Text(addressInfo[0].phoneNumber)
                }
            }
        }


    }
}

@Composable
fun test() {
    Column {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.nonghub_bank),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.giup_bank),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.hana_bank),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
        }
        Spacer(Modifier.size(10.dp))
        Row(
            modifier = Modifier.padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pepole_bank),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.sinhan_bank),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.size(10.dp))
            Image(
                painter = painterResource(id = R.drawable.woori_bank),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.size(10.dp))
        }

    }



}

@Preview
@Composable
fun TestPreview() {
    test()
}