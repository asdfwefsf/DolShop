package com.company.domain.usecase.publicidary

import com.company.domain.entity.PublicDiary
import com.company.domain.repository.publicidary.GetPublicDiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPublicDiaryUseCase @Inject constructor(
    private val getPublicDiaryRepository: GetPublicDiaryRepository
) {
    suspend operator fun invoke() : Flow<List<PublicDiary>> {
        return getPublicDiaryRepository.getPublicDiary()
    }
}