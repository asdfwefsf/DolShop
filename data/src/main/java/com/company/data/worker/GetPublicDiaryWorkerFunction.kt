package com.company.data.worker

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.company.data.worker.test.ImagePagingSource3
import com.company.domain.entity.Diary
import com.company.domain.repository.GetPublicDiaryWorkerFunctionRepository
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPublicDiaryWorkerFunction @Inject constructor(
    private val databaseReference: DatabaseReference,
) : GetPublicDiaryWorkerFunctionRepository {
    override suspend fun callPublicDiaryWorkerFunction(): Flow<PagingData<Diary>> = flow {
        val query = databaseReference.child("images")

        val pagingSourceFactory = { ImagePagingSource3(query) }

        emitAll(
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 20,
                    enablePlaceholders = false,
                    maxSize = 60
                ),
                pagingSourceFactory = { pagingSourceFactory() }
            ).flow
        )

    }
}