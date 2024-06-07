package com.company.data.datasource.advertisement

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AnnouncementInfo::class],
    version = 1,
    exportSchema = false
)
abstract class AnnouncementInfoDatabase : RoomDatabase() {
    abstract val dao : AnnouncemenDao
}