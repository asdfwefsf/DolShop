package com.company.dolshop.screens.screentype.mypagescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.UpdateProductSaleViewModel
import com.company.presentation.R
import com.company.utility.datastore.DataStoreUtility
import com.company.utility.datastore.DataStoreUtility.Companion.isCoupon1Flow
import com.company.utility.datastore.DataStoreUtility.Companion.isCoupon2Flow

@Composable
fun MyCouponScreen(navcontroller: NavController) {
    val saleCouponViewMoel: UpdateProductSaleViewModel = hiltViewModel()
    val context = LocalContext.current

    val Coupon1 = saleCouponViewMoel.savedSaleCoupon1.collectAsState().value
    val Coupon2 = saleCouponViewMoel.savedSaleCoupon2.collectAsState().value
    val dataStoreUtility = DataStoreUtility.getInstance()
    val isCoupon1Boolean =
        dataStoreUtility.run { context.isCoupon1Flow.collectAsStateWithLifecycle(initialValue = false) }
    val isCoupon2Boolean =
        dataStoreUtility.run { context.isCoupon2Flow.collectAsStateWithLifecycle(initialValue = false) }

    when {

        isCoupon1Boolean.value && isCoupon2Boolean.value -> {
            if (Coupon1.isNotEmpty() && Coupon2.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier.background(Color.White).fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("Dol And Shop", color = Color.Black , modifier = Modifier.padding(top = 4.dp , bottom = 4.dp))
                    }
                    val lottie by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(R.raw.salelottie)
                    )
                    LottieAnimation(
                        composition = lottie,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),

                    )
                    CouponCard(Coupon1[0].saleMunGu, Coupon1[0].couponNumber)

                    Spacer(Modifier.size(10.dp))

                    CouponCard(Coupon2[0].saleMunGu, Coupon2[0].couponNumber)

                }
            }

        }

        isCoupon1Boolean.value && !isCoupon2Boolean.value -> {
            if (Coupon1.isNotEmpty() && Coupon2.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier.background(Color.White).fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("Dol And Shop", color = Color.Black , modifier = Modifier.padding(top = 4.dp , bottom = 4.dp))
                    }
                    val lottie by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(R.raw.salelottie)
                    )
                    LottieAnimation(
                        composition = lottie,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),

                        )
                    CouponCard(Coupon1[0].saleMunGu, Coupon1[0].couponNumber)

                    Spacer(Modifier.size(10.dp))

                    CouponCard(Coupon2[0].saleMunGu, Coupon2[0].couponNumber)
                    Text("쿠폰 1은 있고 2만 없을 때")

                }

            }
        }

        !isCoupon1Boolean.value && isCoupon2Boolean.value -> {
            if (Coupon1.isNotEmpty() && Coupon2.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier.background(Color.White).fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text("Dol And Shop", color = Color.Black , modifier = Modifier.padding(top = 4.dp , bottom = 4.dp))
                    }
                    val lottie by rememberLottieComposition(
                        spec = LottieCompositionSpec.RawRes(R.raw.salelottie)
                    )
                    LottieAnimation(
                        composition = lottie,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.padding(start = 15.dp, end = 15.dp),

                        )
                    CouponCard(Coupon1[0].saleMunGu, Coupon1[0].couponNumber)

                    Spacer(Modifier.size(10.dp))

                    CouponCard(Coupon2[0].saleMunGu, Coupon2[0].couponNumber)
                    Text("쿠폰 1은 없고 2만 있을 때")

                }
            }
        }

        !isCoupon1Boolean.value && !isCoupon2Boolean.value -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val lottie by rememberLottieComposition(
                    spec = LottieCompositionSpec.RawRes(R.raw.salelottie)
                )
                LottieAnimation(
                    composition = lottie,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                )


                Text("쿠폰이 없습니다", color = Color.Black)

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, start = 15.dp, end = 15.dp),
                    onClick = {
                        navcontroller.navigate(ScreenList.ProductScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7BF579))
                ) {
                    Text(
                        text = "쿠폰 받으로 가기",
                        color = Color.Black
                    )
                }
            }
        }

        else -> {
            Text("else")
        }
    }


}


@Composable
fun CouponCard(couponMungu: String, couponNumber: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Text(
                text = couponMungu,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = couponNumber,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CouponSogae(text: String, fontSize: TextUnit, fontWeight: FontWeight, color: Color) {
    Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = color)

}