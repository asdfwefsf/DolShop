package com.company.domain.usecase.publicidary

import com.company.domain.entity.PublicDiary
import com.company.domain.repository.publicidary.SavePublicDiaryRepository
import javax.inject.Inject

class SavePublicDiaryUseCase @Inject constructor(
    private val repository : SavePublicDiaryRepository

) {
    suspend operator fun invoke(publicDiary: PublicDiary) {
        repository.savePublicDiary(publicDiary)
    }
}