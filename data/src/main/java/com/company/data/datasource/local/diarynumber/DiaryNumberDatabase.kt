package com.company.data.datasource.local.diarynumber

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DiaryNumberInfo::class],
    version = 1,
    exportSchema = false
)
abstract class DiaryNumberDatabase : RoomDatabase() {
    abstract val dao : DiaryNumberDao
}