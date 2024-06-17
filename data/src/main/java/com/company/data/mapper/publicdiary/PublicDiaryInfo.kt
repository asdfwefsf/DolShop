package com.company.data.mapper.publicdiary

import com.company.data.datasource.local.publicdiary.PublicDiaryInfo
import com.company.domain.entity.PublicDiary

fun PublicDiaryInfo.toPublicDiary(): PublicDiary {

    return PublicDiary(
        id = this.id,
        authNumber = this.authNumber,
        day = this.day,
        diary = this.diary,
        diaryNumber = this.diaryNumber,
        image = this.image,
        love = this.love,
        writer = this.writer
    )

}

fun PublicDiary.toPublicDiaryInfo() : PublicDiaryInfo {

    return PublicDiaryInfo(
        id = this.id,
        authNumber = this.authNumber,
        day = this.day,
        diary = this.diary,
        diaryNumber = this.diaryNumber,
        image = this.image,
        love = this.love,
        writer = this.writer
    )

}