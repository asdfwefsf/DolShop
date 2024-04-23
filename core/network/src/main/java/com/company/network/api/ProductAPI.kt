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
    @Headers("X-JSON-Path: BaseProduct[:]")
    suspend fun getBaseProduct() : Response<List<BaseProductResponse>>
}