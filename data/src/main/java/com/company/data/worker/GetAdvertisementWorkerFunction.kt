package com.company.data.worker

import android.util.Log
import com.company.announcement.api.AnnouncementAPI
import com.company.data.datasource.advertisement.AdvertisementDao
import com.company.data.datasource.advertisement.AdvertisementInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAdvertisementWorkerFunction @Inject constructor(
    private val announcementAPI: AnnouncementAPI,
    private val dao: AdvertisementDao
) {
    suspend fun getAdvertisementInfo() {
        val response = announcementAPI.getAnnouncement1()
        val responseBody = response.body() ?: emptyList()
        if (response.isSuccessful && responseBody != null) {
            withContext(Dispatchers.IO) {
                responseBody.forEach {
                    if (!dao.provideAdvertisementInfoExists(responseBody.size)) {
                        dao.insertAdvertisementInfo(
                            AdvertisementInfo(
                                id = it.id,
                                AdvertisementMunGu = it.AdvertisementMunGu,
                                AdvertisementImage = it.AdvertisementImage
                            )
                        )
//                        dao.deleteAllAdvertisementInfo()

                        Log.d("daoTest", "AdvertisementInfoInsert: ${it.id}")
                    } else {

//                        dao.deleteAllAdvertisementInfo()
                        dao.updateAdvertisementInfo(
                            AdvertisementInfo(
                                id = it.id,
                                AdvertisementMunGu = it.AdvertisementMunGu,
                                AdvertisementImage = it.AdvertisementImage
                            )
                        )
                        Log.d("daoTest", "AdvertisementInfoUpdate : ${it.id}")

                    }

                }
            }
        }
    }
}