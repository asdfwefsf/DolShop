package com.company.data.mapper.advertisement

import com.company.data.datasource.advertisement.AnnouncementInfo
import com.company.domain.entity.Announcement

fun AnnouncementInfo.toAnnouncement() : Announcement {
    return Announcement(
        AnnouncementName = this.AnnouncementName,
        AnnouncementImage = this.AnnouncementImage
    )
}