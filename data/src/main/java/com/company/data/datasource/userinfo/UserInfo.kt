package com.company.data.datasource.userinfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val authNumber : String,
    val authEmail : String,
    val authNickName : String,
    val authProfileImage : String,
    val authAddress : String
)
