package com.company.data.mapper.advertisement

import com.company.data.datasource.local.advertisement.AnnouncementInfo
import com.company.domain.entity.Announcement

fun AnnouncementInfo.toAnnouncement() : Announcement {
    return Announcement(
        announcementName = this.AnnouncementName,
        announcementImage = this.AnnouncementImage
    )
}