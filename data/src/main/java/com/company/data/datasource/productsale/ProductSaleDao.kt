package com.company.data.datasource.productsale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductSaleDao {
    @Upsert
    fun upsertBaseProductInfo(baseProductInfo: ProductSaleInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBaseProductInfo(baseProductInfo: ProductSaleInfo)

    @Update
    fun updateBaseProductInfo(baseProductInfo: ProductSaleInfo)

    @Query("SELECT * FROM baseproductinfo ORDER BY id ASC")
    fun getBaseProductInfo() : Flow<List<ProductSaleInfo>>

    @Query("SELECT EXISTS(SELECT 1 FROM baseproductinfo WHERE id = :id)")
    fun prodideProductExists(id: Int): Boolean


}