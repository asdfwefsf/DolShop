package com.company.dolshop.di

import com.company.dolshop.BuildConfig
import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {



    override fun onCreate() {
        super.onCreate()
//
        var keyHash = Utility.getKeyHash(this)
        Log.i("GlobalApplication", "$keyHash")
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}