package com.company.data.datasource.advertisement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AdvertisementInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val AdvertisementMunGu : String,
    val AdvertisementImage : String
)