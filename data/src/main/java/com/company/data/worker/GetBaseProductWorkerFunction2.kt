package com.company.data.worker

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.company.data.worker.test.ImagePagingSource
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBaseProductWorkerFunction2 @Inject constructor(
    private val databaseReference: DatabaseReference
) {
    fun getImagesFlow(): Flow<PagingData<String>> {
        val query = databaseReference.child("images").child("tagNumber")
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false, maxSize = 50),
            pagingSourceFactory = { ImagePagingSource(query) }
        ).flow
    }


}
