package com.company.data.datasource.baseproductinfo

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseProductDao {
    @Upsert
    fun upsertBaseProductInfo(baseProductInfo: BaseProductInfo)

    @Query("SELECT * FROM baseproductinfo ORDER BY id DESC")
    fun getBaseProductInfo() : Flow<BaseProductInfo>
}