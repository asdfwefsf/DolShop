package com.company.data.datasource.local.advertisement

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface AnnouncemenDao {
    @Upsert
    fun upsertAdvertisementInfo(advertisementInfo: AnnouncementInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAdvertisementInfo(advertisementInfo: AnnouncementInfo)

    @Update
    fun updateAdvertisementInfo(advertisementInfo: AnnouncementInfo)

    @Query("SELECT * FROM AnnouncementInfo ORDER BY id ASC")
    fun getAdvertisementInfo() : Flow<List<AnnouncementInfo>>

    @Query("SELECT EXISTS(SELECT 1 FROM AnnouncementInfo WHERE id = :id)")
    fun provideAdvertisementInfoExists(id: Int): Boolean

    @Query("DELETE FROM AnnouncementInfo")
    fun deleteAllAdvertisementInfo()

}