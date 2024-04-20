package com.company.domain.model

data class UserInfoModel(
    val id : Int = 0,
    val authNumber : String,
    val authEmail : String,
    val authNicName : String,
    val authProfileImage : String
)