package com.company.data.datasource.advertisement

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface AdvertisementDao {
    @Upsert
    fun upsertAdvertisementInfo(advertisementInfo: AdvertisementInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAdvertisementInfo(advertisementInfo: AdvertisementInfo)

    @Update
    fun updateAdvertisementInfo(advertisementInfo: AdvertisementInfo)

    @Query("SELECT * FROM advertisementInfo ORDER BY id ASC")
    fun getAdvertisementInfo() : Flow<List<AdvertisementInfo>>

    @Query("SELECT EXISTS(SELECT 1 FROM advertisementInfo WHERE id = :id)")
    fun provideAdvertisementInfoExists(id: Int): Boolean

    @Query("DELETE FROM advertisementInfo")
    fun deleteAllAdvertisementInfo()

}