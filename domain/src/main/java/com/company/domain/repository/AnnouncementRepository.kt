package com.company.domain.repository

interface AnnouncementRepository {
    suspend fun getAnnouncement()
}