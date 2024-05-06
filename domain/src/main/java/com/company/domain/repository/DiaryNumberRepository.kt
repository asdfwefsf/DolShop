package com.company.domain.repository

interface DiaryNumberRepository {
    suspend fun getDiaryNumber() : Int?
    suspend fun addOneDiaryNumber()
    suspend fun insertDiaryNumber()

}