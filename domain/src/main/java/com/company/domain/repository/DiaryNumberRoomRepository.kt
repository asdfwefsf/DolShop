package com.company.domain.repository

interface DiaryNumberRoomRepository {
    suspend fun getDiaryNumber() : Int?
    suspend fun addOneDiaryNumber()
    suspend fun insertDiaryNumber()

}