package com.company.datasource_local_productsale.productsale

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
    fun upsertProductSaleInfo(productSaleInfo: ProductSaleInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductSaleInfo(productSaleInfo: ProductSaleInfo)

    @Update
    fun updateProductSaleInfo(productSaleInfo: ProductSaleInfo)

    @Query("SELECT * FROM productSaleInfo ORDER BY id ASC")
    fun getProductSaleInfo() : Flow<List<ProductSaleInfo>>

    @Query("SELECT EXISTS(SELECT 1 FROM productSaleInfo WHERE id = :id)")
    fun prodideProductSaleInfoExists(id: Int): Boolean


}