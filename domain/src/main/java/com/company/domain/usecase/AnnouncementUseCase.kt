package com.company.domain.usecase

import com.company.domain.repository.AnnouncementRepository
import javax.inject.Inject

class AnnouncementUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {
    operator suspend fun invoke() {
        announcementRepository.getAnnouncement()
    }
}