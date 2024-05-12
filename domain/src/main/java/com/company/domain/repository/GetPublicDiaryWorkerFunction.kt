package com.company.domain.repository

import androidx.paging.PagingData
import com.company.domain.entity.Diary
import com.company.domain.entity.PublicDiary
import kotlinx.coroutines.flow.Flow

interface GetPublicDiaryWorkerFunctionRepository {
    suspend fun callPublicDiaryWorkerFunction() : Flow<PagingData<PublicDiary>>
}