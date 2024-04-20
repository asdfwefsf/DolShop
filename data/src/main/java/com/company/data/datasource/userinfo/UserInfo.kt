package com.company.data.datasource.userinfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val authNumber : String,
    val authEmail : String,
    val authNicName : String,
    val authProfileImage : String
)
