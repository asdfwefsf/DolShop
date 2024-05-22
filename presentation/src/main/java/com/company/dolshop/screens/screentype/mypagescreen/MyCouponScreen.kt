package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.company.dolshop.viewmodel.UpdateProductSaleViewModel
import com.company.presentation.R
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.isCoupon1Flow
import com.company.utility.DataStoreUtility.Companion.isCoupon2Flow

@Composable
fun MyCouponScreen() {
    val saleCouponViewMoel: UpdateProductSaleViewModel = hiltViewModel()
    val context = LocalContext.current

    val Coupon1 = saleCouponViewMoel.savedSaleCoupon1.collectAsState().value
    val Coupon2 = saleCouponViewMoel.savedSaleCoupon2.collectAsState().value
    val dataStoreUtility = DataStoreUtility.getInstance()
    val isCoupon1Boolean =
        dataStoreUtility.run { context.isCoupon1Flow.collectAsStateWithLifecycle(initialValue = false) }
    val isCoupon2Boolean =
        dataStoreUtility.run { context.isCoupon2Flow.collectAsStateWithLifecycle(initialValue = false) }


    Log.d("Coupon1", "${Coupon1}")
    Log.d("Coupon2", "${Coupon2}")
    Log.d("isCoupon1Boolean", "${isCoupon1Boolean}")
    Log.d("isCoupon2Boolean", "${isCoupon2Boolean}")
    when {
        isCoupon1Boolean.value && isCoupon2Boolean.value -> {
            Log.d("sfjlsjflsjf", "SFsfdsf")
            if (Coupon1.isNotEmpty() && Coupon2.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dolshopshalecoupon),
                            contentDescription = "",
                            modifier = Modifier.size(250.dp)
                        )
                        Column {
                            CouponSogae(text = "고", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "객", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "님", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "께", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                        }
                        Spacer(modifier = Modifier.size(2.dp))
                        Column {
                            CouponSogae(text = "드", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "리", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "는", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                        }
                        Spacer(modifier = Modifier.size(2.dp))
                        Column {
                            CouponSogae(text = "할", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "인", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "쿠", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                            CouponSogae(text = "폰", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold , color = Color.Black)
                        }


                    }

                    CouponCard(Coupon1[0].saleMunGu, Coupon1[0].couponNumber)
                    CouponCard(Coupon2[0].saleMunGu, Coupon2[0].couponNumber)
//                    Text(Coupon1[0].saleMunGu , color = Color.Blue)
//                    Text(Coupon1[0].couponNumber , color = Color.Blue)
//                    Text(Coupon2[0].saleMunGu , color = Color.Blue)
//                    Text(Coupon2[0].couponNumber , color = Color.Blue)
                }
            }

        }

        isCoupon1Boolean.value && !isCoupon2Boolean.value -> {
            if (Coupon1.isNotEmpty() && Coupon2.isNotEmpty()) {

                Column {
                    Text(Coupon1[0].saleMunGu)
                    Text(Coupon1[0].couponNumber)
                    Text("쿠폰 1만 있고 2는 없을 때")
                }
            }
        }

        !isCoupon1Boolean.value && isCoupon2Boolean.value -> {
            if (Coupon1.isNotEmpty() && Coupon2.isNotEmpty()) {

                Column {
                    Text(Coupon2[0].couponNumber)
                    Text(Coupon2[0].couponNumber)
                    Text("쿠폰 1은 없고 2만 있을 때")
                }
            }
        }

        !isCoupon1Boolean.value && !isCoupon2Boolean.value -> {
            Text("쿠폰 1과 2 모두 없을 때")
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
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.Green, Color.Blue)
                    )
                )
                .padding(16.dp)
        ) {
            Text(
                text = couponMungu,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = couponNumber,
                fontSize = 20.sp,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun CouponSogae(text : String, fontSize : TextUnit, fontWeight : FontWeight, color : Color) {
    Text(text = text, fontSize = fontSize, fontWeight = fontWeight , color = color)

}