package com.company.data.datasource.advertisement

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AdvertisementInfo::class],
    version = 1,
    exportSchema = false
)
abstract class AdvertisementInfoDatabase : RoomDatabase() {
    abstract val dao : AdvertisementDao
}