package com.company.data.datasource.local.publicdiary

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PublicDiaryInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val authNumber: String = "",
    val day: String = "",
    val diary: String = "",
    val diaryNumber: String = "",
    val image: String = "",
    val love : String = "",
    val writer : String = ""
)