package com.company.data.datasource.local.productsale

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductSaleInfo::class],
    version = 1,
    exportSchema = false
)
abstract class ProductSaleDataBase : RoomDatabase() {
    abstract val dao : ProductSaleDao
}

