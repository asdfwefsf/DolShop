package com.company.datasource_local_address

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val phoneNumber : String,
    val address : String,
    val detailAddress : String
)