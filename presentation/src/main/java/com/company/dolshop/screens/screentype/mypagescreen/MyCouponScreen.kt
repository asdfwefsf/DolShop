package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.company.dolshop.viewmodel.UpdateProductSaleViewModel
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.isCoupon1Flow
import com.company.utility.DataStoreUtility.Companion.isCoupon2Flow

@Composable
fun MyCouponScreen() {
    val saleCouponViewMoel : UpdateProductSaleViewModel = hiltViewModel()
    val context = LocalContext.current

    val Coupon1 = saleCouponViewMoel.savedSaleCoupon1.collectAsState().value
    val Coupon2 = saleCouponViewMoel.savedSaleCoupon2.collectAsState().value
    val dataStoreUtility = DataStoreUtility.getInstance()
    val isCoupon1Boolean = dataStoreUtility.run { context.isCoupon1Flow.collectAsStateWithLifecycle(initialValue = false) }
    val isCoupon2Boolean = dataStoreUtility.run { context.isCoupon2Flow.collectAsStateWithLifecycle(initialValue = false) }


    Log.d("Coupon1", "${Coupon1}")
    Log.d("Coupon2", "${Coupon2}")
    Log.d("isCoupon1Boolean", "${isCoupon1Boolean}")
    Log.d("isCoupon2Boolean", "${isCoupon2Boolean}")
    when {
        isCoupon1Boolean.value && isCoupon2Boolean.value -> {
            Log.d("sfjlsjflsjf" , "SFsfdsf")
            if (Coupon1.isNotEmpty() && Coupon2.isNotEmpty()) {
                Column {
                    Text(Coupon1[0].saleMunGu , color = Color.Blue)
                    Text(Coupon1[0].couponNumber , color = Color.Blue)
                    Text(Coupon2[0].saleMunGu , color = Color.Blue)
                    Text(Coupon2[0].couponNumber , color = Color.Blue)
                    Text("쿠폰 1과 2 모두 있을 때")
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