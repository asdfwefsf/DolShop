package com.company.domain.usecase.announcement

import com.company.domain.entity.Announcement
import com.company.domain.repository.AnnouncementRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnnouncementUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {
    suspend operator fun invoke() : Flow<List<Announcement>> {
        return announcementRepository.getAnnouncement()
    }
}