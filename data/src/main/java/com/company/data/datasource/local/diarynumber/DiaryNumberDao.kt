package com.company.data.datasource.local.diarynumber

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.company.data.datasource.local.userinfo.UserInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryNumberDao {

    @Insert
    suspend fun insertDiaryNumber(diaryNumberInfo: DiaryNumberInfo)

    @Update
    suspend fun updateDiaryNumber(diaryNumberInfo: DiaryNumberInfo)

    @Delete
    suspend fun deleteDiaryNumber(diaryNumberInfo: DiaryNumberInfo)

    @Transaction
    suspend fun addOneDiaryNumber() {
        val nowNumbr = getDiaryNumber()
        if (nowNumbr != null) {
            nowNumbr.diaryNumber += 1
            updateDiaryNumber(nowNumbr)
        }
    }


    @Query("SELECT * FROM diarynumberinfo ORDER BY id ASC")
    fun getDiaryNumber() : DiaryNumberInfo?

}