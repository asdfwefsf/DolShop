package com.company.network

import com.company.network.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductAPI {
    @GET("b/661bde01ad19ca34f859e2d3?meta=false")
    @Headers("X-JSON-Path: Product[:]")
    suspend fun getProduct(): Response<List<ProductResponse>>
}