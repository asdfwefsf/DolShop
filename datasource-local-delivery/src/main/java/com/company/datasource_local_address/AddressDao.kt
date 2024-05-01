package com.company.datasource_local_address

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAddressInfo(addressInfo: AddressInfo)

    @Update
    fun updateAddressInfo(addressInfo: AddressInfo)
}