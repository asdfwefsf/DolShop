package com.company.dolshop.screens.screentype.productscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.designsystem.Paddings
import com.company.dolshop.viewmodel.getProductViewModel

@Composable
fun ProductScreen(innerPadding : PaddingValues) {
    Text("ProductScreen")
    val getProductViewModel: getProductViewModel = hiltViewModel()
    val productList = getProductViewModel.product.collectAsState()

    LazyColumn(
        modifier = Modifier.padding(innerPadding),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        items(productList.value.size) {
            CategoryItem(

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