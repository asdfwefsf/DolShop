package com.company.domain.repository

import com.company.domain.entity.Advertisement
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {
    suspend fun getAnnouncement() : Flow<List<Advertisement>>
}