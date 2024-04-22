package com.company.dolshop.di

import com.company.network.ProductAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideProductApi() : ProductAPI
    {
        return Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI::class.java)
    }
//    @Provides
//    @Singleton
//    fun provideProductApi(retrofit: Retrofit): ProductAPI =
//        retrofit.create(ProductAPI::class.java)
}