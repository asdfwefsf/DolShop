package com.company.dolshop.screens.screentype.productscreen

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.company.dolshop.designsystem.DolShopTheme
import com.company.dolshop.viewmodel.getProductViewModel
import com.company.domain.model.DomainProductModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductScreen(innerPadding: PaddingValues) {
    val getProductViewModel: getProductViewModel = hiltViewModel()
    val productList = getProductViewModel.product.collectAsState()
    if (productList.value.isEmpty()) {
        CircularProgressIndicator()
    } else {
        Column {
            // add BaseProduct Screen

            firstBaseScreen()
            Spacer(Modifier.padding(20.dp))

            // add BaseProduct Screen

            // add test
            val horizontalPagerState = rememberPagerState(
                pageCount = {
                    10
                },
                initialPage = 0

            )
            HorizontalPager(
                state = horizontalPagerState,
                modifier = Modifier.size(250.dp),
            ) { page ->
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .applyCubic(horizontalPagerState, page)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(test[page])
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { Log.d("haha", "haha") }
                    )
                    LaunchedEffect(key1 = horizontalPagerState.currentPage) {
                        imageLinkViewModel.save(horizontalPagerState.currentPage)
                    }
                }
            }
            // add test

            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.SpaceAround,
            ) {

                items(productList.value.size) {
                    val product = productList.value[it]
                    productItemScreen(
                        product,
                        onClick = {}
                    )

                }
            }
        }

    }
}

@Composable
fun firstBaseScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 64.dp)
            .background(Color.White)
    ) {
        val (person, text, search, shoppingCart) = createRefs()
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(person) {
                    start.linkTo(parent.start)
                }
                .size(40.dp)
                .padding(start = 16.dp)
        )

        Text(
            text = "내 친구 돌돌이",
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(person.end)
                    end.linkTo(search.start)
                    bottom.linkTo(person.bottom)
                }
                .padding(start = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(search) {
                    end.linkTo(shoppingCart.start)
                    bottom.linkTo(person.bottom)

                }
                .padding(end = 24.dp)
                .size(30.dp)

        )

        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(shoppingCart) {
                    end.linkTo(parent.end)
                    bottom.linkTo(person.bottom)

                }
                .size(35.dp)
                .padding(end = 16.dp)

        )

    }

}

@Composable
fun productItemScreen(product: DomainProductModel, onClick: (category: String) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
    ) {
        val (image, text) = createRefs()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .clickable { onClick(product.name) } // Handle click event
        )
        Text(
            text = product.name,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(image.start)
                top.linkTo(image.bottom)
                width = Dimension.fillToConstraints // Fill width within constraints
            }
        )
    }
}

// add test

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.applyCubic(state: PagerState, page: Int, horizontal: Boolean = true): Modifier {
    return graphicsLayer {
        val pageOffset = state.offsetForPage(page)
        val offScreenRight = pageOffset < 0f
        val def = if (horizontal) {
            105f
        } else {
            -90f
        }
        val interpolated = FastOutLinearInEasing.transform(pageOffset.absoluteValue)
        val rotation = (interpolated * if (offScreenRight) def else -def).coerceAtMost(90f)
        if (horizontal) {
            rotationY = rotation
        } else {
            rotationX = rotation
        }

        transformOrigin = if (horizontal) {
            TransformOrigin(
                pivotFractionX = if (offScreenRight) 0f else 1f,
                pivotFractionY = .5f
            )
        } else {
            TransformOrigin(
                pivotFractionY = if (offScreenRight) 0f else 1f,
                pivotFractionX = .5f
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

// add test

@Preview
@Composable
fun testFirstBaseScreen() {
    DolShopTheme {
        firstBaseScreen()
    }
}
