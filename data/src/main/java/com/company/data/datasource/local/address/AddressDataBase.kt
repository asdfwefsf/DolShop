package com.company.data.datasource.local.address

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AddressInfo::class],
    version = 1,
    exportSchema = false
)
abstract class AddressDataBase : RoomDatabase() {
    abstract val dao : AddressDao
}