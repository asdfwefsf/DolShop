package com.company.domain.repository

import androidx.paging.PagingData
import com.company.domain.entity.Diary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface getDiaryWorkerFunctionRepository  {
    suspend fun callDiaryWorkerFunction() : Flow<PagingData<Diary>>
}