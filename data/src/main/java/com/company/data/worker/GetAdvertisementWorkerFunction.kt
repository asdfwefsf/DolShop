package com.company.data.worker

import android.util.Log
import com.company.announcement.api.AnnouncementAPI
import com.company.data.datasource.advertisement.AnnouncemenDao
import com.company.data.datasource.advertisement.AnnouncementInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAdvertisementWorkerFunction @Inject constructor(
    private val announcementAPI: AnnouncementAPI,
    private val dao: AnnouncemenDao
) {
    suspend fun getAdvertisementInfo() {
        val response = announcementAPI.getAnnouncement1()
        val responseBody = response.body() ?: emptyList()
        if (response.isSuccessful && responseBody != null) {
            withContext(Dispatchers.IO) {
                responseBody.forEach {
                    if (!dao.provideAdvertisementInfoExists(responseBody.size)) {
                        dao.insertAdvertisementInfo(
                            AnnouncementInfo(
                                id = it.id,
                                AnnouncementName = it.AnnouncementName,
                                AnnouncementImage = it.AnnouncementImage
                            )
                        )
//                        dao.deleteAllAdvertisementInfo()

                        Log.d("daoTest", "AdvertisementInfoInsert: ${it.id}")
                    } else {

//                        dao.deleteAllAdvertisementInfo()
                        dao.updateAdvertisementInfo(
                            AnnouncementInfo(
                                id = it.id,
                                AnnouncementName = it.AnnouncementName,
                                AnnouncementImage = it.AnnouncementImage
                            )
                        )
                        Log.d("daoTest", "AdvertisementInfoUpdate : ${it.id}")

                    }

                }
            }
        }
    }
}