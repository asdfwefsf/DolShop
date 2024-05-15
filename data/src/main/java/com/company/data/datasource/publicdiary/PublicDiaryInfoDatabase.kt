package com.company.data.datasource.publicdiary

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PublicDiaryInfo::class],
    version = 1,
    exportSchema = false
)
abstract class PublicDiaryInfoDatabase : RoomDatabase() {
    abstract val dao : PublicDiaryDao
}