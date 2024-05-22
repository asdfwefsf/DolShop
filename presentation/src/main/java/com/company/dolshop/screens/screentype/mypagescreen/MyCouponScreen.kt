package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    val dataStoreUtility = DataStoreUtility.getInstance()
    val isCoupon1Boolean = dataStoreUtility.run { context.isCoupon1Flow.collectAsStateWithLifecycle(initialValue = false) }
    val isCoupon2Boolean = dataStoreUtility.run { context.isCoupon2Flow.collectAsStateWithLifecycle(initialValue = false) }
    Log.d("MyCouponScreen", "isCoupon1Boolean: ${isCoupon1Boolean.value}, isCoupon2Boolean: ${isCoupon2Boolean.value}")

    when {
        isCoupon1Boolean.value && isCoupon2Boolean.value -> {
            Text("쿠폰 1과 2 모두 있을 때")
        }
        isCoupon1Boolean.value && !isCoupon2Boolean.value -> {
            Text("쿠폰 1만 있고 2는 없을 때")
        }
        !isCoupon1Boolean.value && isCoupon2Boolean.value -> {
            Text("쿠폰 1은 없고 2만 있을 때")
        }
        !isCoupon1Boolean.value && !isCoupon2Boolean.value -> {
            Text("쿠폰 1과 2 모두 없을 때")
        }
        else -> {
            Text("else")
        }
    }



}