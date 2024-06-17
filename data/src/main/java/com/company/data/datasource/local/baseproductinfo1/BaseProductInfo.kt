package com.company.data.datasource.local.baseproductinfo1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BaseProductInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val image : String,
    val name : String
)
