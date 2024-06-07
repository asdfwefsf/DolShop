package com.company.domain.repository

import com.company.domain.entity.Announcement
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {
    suspend fun getAnnouncement() : Flow<List<Announcement>>
}