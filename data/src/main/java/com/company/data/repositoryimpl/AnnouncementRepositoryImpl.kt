package com.company.data.repositoryimpl

import com.company.data.datasource.advertisement.AnnouncemenDao
import com.company.data.mapper.advertisement.toAnnouncement
import com.company.domain.entity.Announcement
import com.company.domain.repository.AnnouncementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
    private val dao : AnnouncemenDao
) : AnnouncementRepository{

    override suspend fun getAnnouncement(): Flow<List<Announcement>> {
        return dao.getAdvertisementInfo().map {
            it.map {
                it.toAnnouncement()
            }
        }
    }
}