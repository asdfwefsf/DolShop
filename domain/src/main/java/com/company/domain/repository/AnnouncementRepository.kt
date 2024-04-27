package com.company.domain.repository

import com.company.domain.model.DomainAnnouncementModel
import kotlinx.coroutines.flow.Flow

interface AnnouncementRepository {
    fun getAnnouncement() : Flow<List<DomainAnnouncementModel>>
}