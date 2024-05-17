package com.company.data.repositoryimpl

import com.company.data.datasource.advertisement.AdvertisementDao
import com.company.data.mapper.advertisement.toAdvertisement
import com.company.domain.entity.Advertisement
import com.company.domain.repository.AnnouncementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
//    private val announcementAPI: AnnouncementAPI
    private val dao : AdvertisementDao
) : AnnouncementRepository{

    override suspend fun getAnnouncement(): Flow<List<Advertisement>> {
        return dao.getAdvertisementInfo().map {
            it.map {
                it.toAdvertisement()
            }
        }
    }
}