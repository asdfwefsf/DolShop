package com.company.data.datasource.baseproductinfo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BaseProductInfo::class],
    version = 1,
    exportSchema = false
)
abstract class BaseProductInfoDataBase : RoomDatabase() {
    abstract val dao : BaseProductDao
}