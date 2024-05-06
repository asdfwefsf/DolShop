package com.company.data.datasource.diarynumber

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DiaryNumberInfo (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var diaryNumber : Int
)