package com.company.data.datasource.userinfo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserInfo::class],
    version = 1,
    exportSchema = false
)
abstract class UserInfoDatabase : RoomDatabase() {
    abstract val dao : UserInfoDao
}