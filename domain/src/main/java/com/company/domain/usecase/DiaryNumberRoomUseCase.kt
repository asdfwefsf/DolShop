package com.company.domain.usecase

import com.company.domain.repository.DiaryNumberRoomRepository
import javax.inject.Inject

class DiaryNumberRoomUseCase @Inject constructor(
    private val diaryNumberRepository: DiaryNumberRoomRepository
) {
    suspend operator fun invoke() : Int? {
        return diaryNumberRepository.getDiaryNumber()
    }

    suspend fun addOneDiaryNumber() {
        diaryNumberRepository.addOneDiaryNumber()
    }

    suspend fun insertDiaryNumber() {
        diaryNumberRepository.insertDiaryNumber()
    }
}