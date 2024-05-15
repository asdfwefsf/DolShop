package com.company.domain.repository.publicidary

import com.company.domain.entity.PublicDiary
import kotlinx.coroutines.flow.Flow

interface GetPublicDiaryRepository {
    suspend fun getPublicDiary() : Flow<List<PublicDiary>>
}