package com.company.domain.usecase.announcement

import com.company.domain.model.DomainAnnouncementModel
import com.company.domain.model.DomainProductModel
import com.company.domain.repository.AnnouncementRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnnouncementUseCase @Inject constructor(
    private val announcementRepository: AnnouncementRepository
) {
    operator suspend fun invoke() : Flow<List<DomainAnnouncementModel>> {
        return announcementRepository.getAnnouncement()
    }
}