package com.company.dolshop.screens.screentype.productscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.company.designsystem.designsystem.component.loadcoil.LoadDetailProductScreen
import com.company.dolshop.screens.ScreenList
import com.company.domain.model.DomainProductModel
import com.company.domain.model.GumaeProductModel
import com.company.utility.decodeUrl
import com.company.utility.encodeUrl
import com.google.gson.Gson

@Composable
fun DetailProductScreen(dolURL: DomainProductModel, navController: NavController) {
    val decodedDolUrl = decodeUrl(dolURL.toString())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            DolInfoScreen(dolURL)
        }
        item {
            GoToGuMaeScreen(dolURL, navController)
        }


    }


}

@Composable
fun DolInfoScreen(
    dolURL: DomainProductModel
) {

//    var decodedDomainProductModel = decodeUrl(dolURL.toString())
//    val decodedText = Gson().fromJson(decodedDomainProductModel , DomainProductModel::class.java)
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(start = 4.dp , end = 4.dp)
    ) {
        LoadDetailProductScreen(
            imageUrl = dolURL.image1,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 4.dp)
        )
        Text(decodeUrl(dolURL.text1), color = Color.Black)
        LoadDetailProductScreen(
            imageUrl = dolURL.image2,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(decodeUrl(dolURL.text2), color = Color.Black)
        LoadDetailProductScreen(
            imageUrl = dolURL.image3,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text(decodeUrl(dolURL.text3), color = Color.Black)
        LoadDetailProductScreen(
            imageUrl = dolURL.image4,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Text(decodeUrl(dolURL.text4), color = Color.Black)
        LoadDetailProductScreen(
            imageUrl = dolURL.image5,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
        Text(decodeUrl(dolURL.text5), color = Color.Black)
    }
}

@Composable
fun GoToGuMaeScreen(dolURL: DomainProductModel, navController: NavController) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, start = 15.dp, end = 15.dp , bottom = 15.dp),
        onClick = {
            navController.navigate("${ScreenList.GuMaeScreen.route}/${encodeUrl(Gson().toJson(dolURL))}")
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
    ) {
        Text(
            text = "구매하기",
            color = Color.Black
        )
    }
}