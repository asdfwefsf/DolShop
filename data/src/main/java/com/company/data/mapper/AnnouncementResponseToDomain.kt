package com.company.data.mapper

import com.company.domain.model.DomainAnnouncementModel
import com.company.network.model.AnnouncementResponse

fun AnnouncementResponse.toDomainAnnouncementModel(): DomainAnnouncementModel {
    return DomainAnnouncementModel(
        image = this.image,
        name = this.name
    )
}
