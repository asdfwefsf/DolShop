package com.company.domain.usecase.publicidary

import com.company.domain.entity.PublicDiary
import com.company.domain.repository.publicidary.DeletePublicDiaryRepository
import javax.inject.Inject

class DeletePublicDiaryUseCase @Inject constructor(
    private val deletePublicDiaryRepository : DeletePublicDiaryRepository
) {
    suspend operator fun invoke(publicDiary: PublicDiary) {
        deletePublicDiaryRepository.deletePublicDiary(publicDiary)
    }
}