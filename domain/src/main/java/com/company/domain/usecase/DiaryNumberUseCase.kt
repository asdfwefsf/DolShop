package com.company.domain.usecase

import com.company.domain.repository.DiaryNumberRepository
import javax.inject.Inject

class DiaryNumberUseCase @Inject constructor(
    private val diaryNumberRepository: DiaryNumberRepository
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