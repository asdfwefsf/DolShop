package com.company.data.mapper.publicdiary

import com.company.data.datasource.publicdiary.PublicDiaryInfo
import com.company.domain.entity.PublicDiary

fun PublicDiaryInfo.toPublicDiary(): PublicDiary {

    return PublicDiary(
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
        authNumber = this.authNumber,
        day = this.day,
        diary = this.diary,
        diaryNumber = this.diaryNumber,
        image = this.image,
        love = this.love,
        writer = this.writer
    )
}