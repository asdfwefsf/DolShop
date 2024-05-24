package com.company.dolshop.screens.screentype.productscreen

import android.util.Log
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.UpdateBaseProductViewModel
import com.company.dolshop.viewmodel.UpdateProductSaleViewModel
import com.company.dolshop.viewmodel.getProductViewModel
import com.company.domain.model.DomainBaseProductModel
import com.company.domain.model.DomainProductModel
import com.company.presentation.R
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.isCoupon1Flow
import com.company.utility.DataStoreUtility.Companion.isCoupon2Flow
import com.company.utility.DataStoreUtility.Companion.setCoupon1State
import com.company.utility.DataStoreUtility.Companion.setCoupon2State
import com.company.utility.encodeUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductScreen(innerPadding: PaddingValues, count: Int, navController: NavController) {
    val getProductViewModel: getProductViewModel = hiltViewModel()
    val changPproductList = getProductViewModel.product.collectAsState()

    val updateBaseProductViewModel: UpdateBaseProductViewModel = hiltViewModel()
    val baseProductList = updateBaseProductViewModel.Product.collectAsState()

    val productSaleViewModel: UpdateProductSaleViewModel = hiltViewModel()
    val saleMunGu = productSaleViewModel.mungu.collectAsState()


    val listState = rememberLazyListState()
    val horizontalPagerState = rememberPagerState(
        pageCount = {
            baseProductList.value.size
        },
        initialPage = count
    )

//    if (changPproductList.value.isEmpty() || baseProductList.value.isEmpty()) {
//        CircularProgressIndicator()
//        Log.d("circular", "loading")
//    } else {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = listState
    ) {
        item {
            firstBaseScreen()
        }
        item {
            secondBaseScreen(horizontalPagerState, updateBaseProductViewModel)
        }
        item {
            fourthBaseScreen(productSaleViewModel, navController)
        }

        item {
            thirdBaseScreen()
        }
        item {
            firstChangeScreen(changPproductList, listState, getProductViewModel, navController)
        }

    }
}

@Composable
fun firstBaseScreen() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        val (person, text, search, shoppingCart) = createRefs()
        // 나중에 사용
//        Icon(
//            imageVector = Icons.Default.Person,
//            contentDescription = null,
//            modifier = Modifier
//                .constrainAs(person) {
//                    start.linkTo(parent.start)
//                }
//                .size(40.dp)
//                .padding(start = 16.dp)
//        )
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
        // 나중에 사용
//        Icon(
//            imageVector = Icons.Default.Search,
//            contentDescription = null,
//            modifier = Modifier
//                .constrainAs(search) {
//                    end.linkTo(shoppingCart.start)
//                    bottom.linkTo(person.bottom)
//                }
//                .padding(end = 24.dp)
//                .size(30.dp)
//        )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun secondBaseScreen(pagerState: PagerState, viewmodel: UpdateBaseProductViewModel) {
    val isVisible = remember { mutableStateOf(false) }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .onGloballyPositioned { isVisible.value = true }
            .onSizeChanged { if (it.width > 0 && it.height > 0) isVisible.value = true },
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .applyCubic(pagerState, page)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(viewmodel.Product.collectAsState().value[page].image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { Log.d("haha", "haha") }
            )
            Text(
                text = viewmodel.Product.collectAsState().value[page].name,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Color.White.copy(alpha = 0.5f))
                    .padding(8.dp),
                color = Color.Black
            )
            LaunchedEffect(key1 = pagerState.currentPage) {
                viewmodel.save(pagerState.currentPage)
            }
        }
    }
    if (isVisible.value) {
        LaunchedEffect(key1 = Unit) {
            viewmodel.save(pagerState.currentPage)
            while (isActive) {
                delay(3000)
                val nextPage = (pagerState.currentPage + 1) % viewmodel.Product.value.size
                Log.d("nextPage", (nextPage + 1).toString())
                pagerState.animateScrollToPage(
                    nextPage,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }
}

@Composable
fun thirdBaseScreen() {
    Column(
        modifier = Modifier.padding(top = 4.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        circleBaseItem1()
        circleBaseItem2()
    }
}

@Composable
fun circleBaseItem1() {
    val iconList = listOf(
        "회사1",
        "회사2",
        "회사3",
        "회사4",
        "회사5",
        "회사6",
        "회사7",
        "회사8",
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(iconList.size) { index ->
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                    )
                    Text(
                        text = iconList[index],
                        modifier = Modifier
                            .padding(top = 8.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun circleBaseItem2() {
    val iconList = listOf(
        "회사9",
        "회사10",
        "회사11",
        "회사12",
        "회사13",
        "회사14",
        "회사15",
        "회사16",
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(iconList.size) { index ->
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                    )
                    Text(
                        text = iconList[index],
                        modifier = Modifier
                            .padding(top = 8.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun fourthBaseScreen(viewModel: UpdateProductSaleViewModel, navController: NavController) {
    val munguState = viewModel.mungu.collectAsState()

    var showCoupon1Dialog = remember { mutableStateOf(false) }
    var showCoupon2Dialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),

        ) {
        Column {
            if (munguState.value.size > 0) {
                Row {
                    Text(
                        munguState.value[0].saleMunGu,
                        color = Color.White,
                        modifier = Modifier.clickable {
//                            showCouponDialog.value = true
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            showCoupon1Dialog.value = true
                        }
                    )
                }

                Row {
                    Text(
                        munguState.value[1].saleMunGu,
                        color = Color.White,
                        modifier = Modifier.clickable {
//                            showCouponDialog.value = true
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            showCoupon2Dialog.value = true
                        }
                    )
                }

            }
        }

    }
    val dataStoreUtility = DataStoreUtility.getInstance()
    val context = LocalContext.current

    val coupon1Boolean by dataStoreUtility.run {
        context.isCoupon1Flow.collectAsStateWithLifecycle(
            initialValue = false
        )
    }

    val coupon2Boolean by dataStoreUtility.run {
        context.isCoupon2Flow.collectAsStateWithLifecycle(
            initialValue = false
        )
    }

    if (showCoupon1Dialog.value) {
        Coupon1Dialog(showCoupon1Dialog, coupon1Boolean, navController)
//        showCoupon1Dialog.value = false
    }

    if (showCoupon2Dialog.value) {
        Coupon2Dialog(showCoupon2Dialog, coupon2Boolean, navController)
//        showCoupon2Dialog.value = false

    }

}

@Composable
fun Coupon1Dialog(
    dialogBoolean: MutableState<Boolean>,
    coupon1Boolean: Boolean,
    navController: NavController
) {
    val dataStoreUtility = DataStoreUtility.getInstance()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val viewmodel: UpdateProductSaleViewModel = hiltViewModel()

    if (coupon1Boolean) {
        AlertDialog(
            onDismissRequest = { dialogBoolean.value = false },
            title = { Text("이미 쿠폰1이 있습니다. 쿠폰함으로 가시겠습니까?") },
            confirmButton = {
                Button(
                    onClick = {
                        dialogBoolean.value = false
//                        viewmodel.setSavedSaleCoupon1Boolean(true)
                        navController.navigate(ScreenList.MyCouponScreen.route)
                    }
                ) {
                    Text("확인")
                }
            }
        )
    } else {
        AlertDialog(
            onDismissRequest = { dialogBoolean.value = false },
            title = { Text("쿠폰1을 저장하시겠습니까 ?") },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            dataStoreUtility.apply {
                                context.setCoupon1State(true)
                            }
                            dialogBoolean.value = false
                        }
                    }
                ) {
                    Text("확인")
                }
            }
        )
    }

}


@Composable
fun Coupon2Dialog(
    dialogBoolean: MutableState<Boolean>,
    coupon2Boolean: Boolean,
    navController: NavController
) {
    val dataStoreUtility = DataStoreUtility.getInstance()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val viewmodel: UpdateProductSaleViewModel = hiltViewModel()

    if (coupon2Boolean) {
        AlertDialog(
            onDismissRequest = { dialogBoolean.value = false },
            title = { Text("이미 쿠폰2이 있습니다. 쿠폰함으로 가시겠습니까?") },
            confirmButton = {
                Button(
                    onClick = {
                        dialogBoolean.value = false
//                        viewmodel.setSavedSaleCoupon2Boolean(true)

                        navController.navigate(ScreenList.MyCouponScreen.route)

                    }
                ) {
                    Text("확인")
                }
            }
        )
    } else {
        AlertDialog(
            onDismissRequest = { dialogBoolean.value = false },
            title = { Text("쿠폰2을 저장하시겠습니까 ?") },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            dataStoreUtility.apply {
                                context.setCoupon2State(true)
                            }
                            dialogBoolean.value = false
                        }
                    }
                ) {
                    Text("확인")
                }
            }
        )
    }


}


@Composable
fun firstChangeScreen(
    changPproductList: State<List<DomainProductModel>>,
    lazyListState: LazyListState,
    viewmodel: getProductViewModel,
    navController: NavController
) {
    var test by remember { mutableStateOf(lazyListState.canScrollForward) }
    LaunchedEffect(key1 = test) {
        Log.d("launchedEffect", "안움직여")
        if (test) {
            Log.d("launchedEffect", "viewmodel 동작")
            viewmodel.test()
            test = false
        }
    }
    Column {
        (1..14).forEach {
            Text("S")
            Text("S")
            Text("S")
        }
        (1..changPproductList.value.size).forEach {
            val product = changPproductList.value[it - 1]
            productItemScreen(
                product,
                onClick = { dolURL ->
                    Log.d("sisflsfjlsjf" , dolURL)
//                    navController.navigate("${ScreenList.DetailProductScreen.route}/$dolURL")
//                    navController.navigate(ScreenList.DetailProductScreen.route + dolURL)
                    navController.navigate("${ScreenList.DetailProductScreen.route}/${dolURL}")


                },
            )
        }
    }
    if (lazyListState.isScrollInProgress) {
        Log.d("launchedEffect", "움직이는중이네요")
        test = true
    }
}

@Composable
fun productItemScreen(
    productState: DomainProductModel,
    onClick: (dolUrl: String) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f)
    ) {
        val (image, text) = createRefs()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(productState.image1)
                .placeholder(R.drawable.ic_launcher_background)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .clickable {
                    onClick(encodeUrl(productState.image1))
                }
        )
        Text(
            text = productState.text1,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(image.start)
                top.linkTo(image.bottom)
                width = Dimension.fillToConstraints
            }
        )
    }
}

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

@Preview
@Composable
fun testFirstBaseScreen() {
    com.company.designsystem.designsystem.DolShopTheme {
        firstBaseScreen()
    }
}

@Preview()
@Composable
fun circleBaseItem1Preview() {
    val iconList = listOf(
        "spring\nseason off",
        "나이키",
        "라쉬",
        "뷰티",
        "아웃렛",
        "e",
        "뷰티위크",
        "무신사에디션",
        "신발",
        "키즈",
        "플레이어",
        "스포츠"
    )
    com.company.designsystem.designsystem.DolShopTheme {
        circleBaseItem1()
    }
}

@Preview
@Composable
fun testThirdBaseScreen() {
    com.company.designsystem.designsystem.DolShopTheme {
        thirdBaseScreen()
    }
}
