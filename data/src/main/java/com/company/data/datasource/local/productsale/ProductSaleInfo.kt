package com.company.data.datasource.local.productsale

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductSaleInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val saleMunGu : String,
    val couponNumber : String
)