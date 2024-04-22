package com.company.dolshop.screens.screentype.productscreen

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.company.dolshop.designsystem.Paddings
import com.company.dolshop.viewmodel.getProductViewModel
import com.company.domain.model.DomainProductModel
import com.company.presentation.R
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ProductScreen(innerPadding: PaddingValues) {
    val getProductViewModel: getProductViewModel = hiltViewModel()
    val productList = getProductViewModel.product.collectAsState()
    Text("ProductScreen")
    if (productList.value.isEmpty()) {
        CircularProgressIndicator()
    } else {
        Column {

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

    }
}


@Composable
fun productItemScreen(Image: String, Name: String, onClick: (category: String) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.25f)
    ) {
        val (image, text) = createRefs()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .clickable { onClick(Name) } // Handle click event
        )
        Text(
            text = Name,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(image.start)
                top.linkTo(image.bottom)
                width = Dimension.fillToConstraints // Fill width within constraints
            }
        )
    }

//    Box(
//        modifier = Modifier
//            .fillMaxWidth(0.5f)
//            .fillMaxHeight(0.25f)
//
//    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(Image)
//                .crossfade(true)
//                .build(),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxSize()
//                .clickable { Log.d("haha", "haha") }
//        )
//    }
//    Text(text = Name)
}
