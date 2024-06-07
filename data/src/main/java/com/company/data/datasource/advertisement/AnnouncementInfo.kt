package com.company.data.datasource.advertisement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnnouncementInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val AnnouncementName : String,
    val AnnouncementImage : String
)