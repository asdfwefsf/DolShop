package com.company.data.datasource.userinfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {
    @Upsert
    suspend fun upsertUserInfo(userInfo: UserInfo)

    @Delete
    suspend fun deleteUserInfo(userInfo: UserInfo)

    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getUserInfo() : Flow<UserInfo>


}