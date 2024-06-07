package com.company.data.datasource.address

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val addressName : String,
    val addressNumber : String,
    val address : String,
    val addressDetailName : String,
    val phoneNumber : String
)