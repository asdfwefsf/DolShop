package com.company.datasource_local_address

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAddressInfo(addressInfo: AddressInfo)

    @Update
    fun updateAddressInfo(addressInfo: AddressInfo)

    @Query("SELECT * FROM addressInfo ORDER BY id DESC")
    fun getAddressInfo() : Flow<List<AddressInfo>>
}