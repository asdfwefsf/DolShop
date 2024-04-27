package com.company.network.api

import com.company.network.model.BaseProductResponse
import com.company.network.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductAPI {
    @GET("b/661bde01ad19ca34f859e2d3?meta=false")
    @Headers("X-JSON-Path: Product[:]")
    suspend fun getProduct() : Response<List<ProductResponse>>
    @GET("b/661bde01ad19ca34f859e2d3?meta=false")
    @Headers("X-JSON-Path: BaseProduct1[:]")
    suspend fun getBaseProduct1() : Response<List<BaseProductResponse>>
    @GET("b/661bde01ad19ca34f859e2d3?meta=false")
    @Headers("X-JSON-Path: BaseProduct2[:]")
    suspend fun getBaseProduct2() : Response<List<BaseProductResponse>>
    @GET("b/661bde01ad19ca34f859e2d3?meta=false")
    @Headers("X-JSON-Path: Announcement1[:]")
    suspend fun getAnnouncement1() : Response<List<BaseProductResponse>>
}