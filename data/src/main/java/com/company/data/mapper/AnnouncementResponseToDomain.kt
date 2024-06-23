package com.company.data.mapper

import com.company.announcement.model.AnnouncementResponse
import com.company.domain.model.DomainAnnouncementModel

fun AnnouncementResponse.toDomainAnnouncementModel(): DomainAnnouncementModel {
    return DomainAnnouncementModel(
        announcementName = this.AnnouncementName,
        announcementImage = this.AnnouncementImage
    )

}


