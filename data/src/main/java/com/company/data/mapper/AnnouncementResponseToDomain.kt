package com.company.data.mapper

import com.company.announcement.model.AnnouncementResponse
import com.company.domain.model.DomainAnnouncementModel

fun AnnouncementResponse.toDomainAnnouncementModel(): DomainAnnouncementModel {
    return DomainAnnouncementModel(
        AdvertisementMunGu = this.AdvertisementMunGu,
        AdvertisementImage = this.AdvertisementImage
    )

}


