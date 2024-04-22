package com.company.dolshop.screens.screentype.productscreen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.company.dolshop.designsystem.Paddings
import com.company.dolshop.viewmodel.getProductViewModel

@Composable
fun ProductScreen(innerPadding : PaddingValues) {
    val getProductViewModel: getProductViewModel = hiltViewModel()
    val productList = getProductViewModel.product.collectAsState()
    Text("ProductScreen")

    LazyColumn(
        modifier = Modifier.padding(innerPadding),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        items(productList.value.size) {
            productItemScreen(
                Image = productList.value[it].image,
                Name = productList.value[it].name,
                onClick = {}
            )

        }
    }
}

@Composable
fun CategoryItem(Image: String, Name: String, onClick: (category: String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEEEEEE)),
//        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = Name,
            fontSize = 18.sp,
            color = Color.Black,
//            modifier = Modifier.padding(0.dp, 8.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = Image,
            fontSize = 18.sp,
            color = Color.Black,
//            modifier = Modifier.padding(0.dp, 8.dp),
        )
    }
}

@Composable
fun productItemScreen(Image: String, Name: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.25f)

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable { Log.d("haha", "haha") }
        )
    }
    Text(text = Name)
}
