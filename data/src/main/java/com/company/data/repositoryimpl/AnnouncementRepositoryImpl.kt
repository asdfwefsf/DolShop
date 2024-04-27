package com.company.data.repositoryimpl

import com.company.domain.repository.AnnouncementRepository
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(

) : AnnouncementRepository{
    override suspend fun getAnnouncement() {
        TODO("Not yet implemented")
    }
}