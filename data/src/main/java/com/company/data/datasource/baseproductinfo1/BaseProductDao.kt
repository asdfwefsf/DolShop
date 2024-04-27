package com.company.data.datasource.baseproductinfo1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseProductDao {
    @Upsert
    fun upsertBaseProductInfo(baseProductInfo: BaseProductInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBaseProductInfo(baseProductInfo: BaseProductInfo)

    @Update
    fun updateBaseProductInfo(baseProductInfo: BaseProductInfo)

    @Query("SELECT * FROM baseproductinfo ORDER BY id DESC")
    fun getBaseProductInfo() : Flow<List<BaseProductInfo>>

    @Query("SELECT EXISTS(SELECT 1 FROM baseproductinfo WHERE id = :id)")
    fun prodideProductExists(id: Int): Boolean


}