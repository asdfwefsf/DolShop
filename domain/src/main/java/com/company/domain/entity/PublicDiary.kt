package com.company.domain.entity

data class PublicDiary(
    val id : Int = 0,
    val authNumber: String = "",
    val day: String = "",
    val diary: String = "",
    val diaryNumber: String = "",
    val image: String = "",
    var love : String = "",
    val writer : String = ""
)
//val day: String = "",
//val diary: String = "",
//val image: String = "",
//val love: String = "",
//val writer: String = "",
//val authNumber : String = "",
//val diaryNumber : String = ""