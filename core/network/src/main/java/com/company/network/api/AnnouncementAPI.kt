package com.company.network.api

import com.company.network.model.AnnouncementResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface AnnouncementAPI {
    @GET("b/661bde01ad19ca34f859e2d3?meta=false")
    @Headers("X-JSON-Path: Announcement1[:]")
    suspend fun getAnnouncement1() : Response<List<AnnouncementResponse>>
}