package com.company.data.datasource.userinfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.company.data.datasource.baseproductinfo1.BaseProductInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {
    @Upsert
    suspend fun upsertUserInfo(userInfo: UserInfo)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserInfo(userInfo: UserInfo)
    @Update
    fun updateUserInfo(userInfo: UserInfo)
    @Delete
    suspend fun deleteUserInfo(userInfo: UserInfo)

    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getUserInfo() : Flow<UserInfo>

    @Query("SELECT EXISTS(SELECT 1 FROM userinfo WHERE id = :id)")
    fun prodideUserInfoExists(id: Int): Boolean
}