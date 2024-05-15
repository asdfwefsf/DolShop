package com.company.data.datasource.publicdiary

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface PublicDiaryDao {

    @Upsert
    fun upsertPublicDiaryInfo(publicDiary: PublicDiaryInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPublicDiaryInfo(publicDiary: PublicDiaryInfo)

    @Update
    fun updatePublicDiaryInfo(publicDiary: PublicDiaryInfo)

    @Query("SELECT EXISTS(SELECT 1 FROM PublicDiaryInfo WHERE image = :image)")
    fun PublicDiarySameThingBoolean(image: String): Boolean

}