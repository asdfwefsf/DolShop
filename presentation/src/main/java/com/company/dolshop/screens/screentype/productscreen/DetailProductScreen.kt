package com.company.dolshop.screens.screentype.productscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun DetailProductScreen(dolURL : DomainProductModel , navController: NavController) {
    val decodedDolUrl = decodeUrl(dolURL.toString())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            DolInfoScreen(dolURL)
        }
        item {
            GoToGuMaeScreen(dolURL , navController)
        }


    }



}

@Composable
fun DolInfoScreen(
    dolURL : DomainProductModel
) {

//    var decodedDomainProductModel = decodeUrl(dolURL.toString())
//    val decodedText = Gson().fromJson(decodedDomainProductModel , DomainProductModel::class.java)
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text("Detail Product")
        LoadDetailProductScreen(
            imageUrl = dolURL.image1,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(decodeUrl(dolURL.text1))
        LoadDetailProductScreen(
            imageUrl = dolURL.image2,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(decodeUrl(dolURL.text2))
        LoadDetailProductScreen(
            imageUrl = dolURL.image3,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text(decodeUrl(dolURL.text3))
        LoadDetailProductScreen(
            imageUrl = dolURL.image4,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
        Text(decodeUrl(dolURL.text4))
        LoadDetailProductScreen(
            imageUrl = dolURL.image5,
            context = LocalContext.current,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
        Text(decodeUrl(dolURL.text5))
    }
}

@Composable
fun GoToGuMaeScreen(dolURL: DomainProductModel , navController: NavController) {
    Text("구매하기" , modifier = Modifier.clickable {
        val GuMaeInfo = dolURL.text1
        navController.navigate("${ScreenList.GuMaeScreen.route}/${encodeUrl(Gson().toJson(dolURL))}")
    })
}