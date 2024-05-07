package com.company.data.worker

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.company.data.datasource.userinfo.UserInfoDao
import com.company.data.worker.test.ImagePagingSource
import com.company.domain.entity.Diary
import com.company.domain.repository.getDiaryWorkerFunctionRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GetDiaryWorkerFunction @Inject constructor(
    private val databaseReference: DatabaseReference,
    private val dao: UserInfoDao
) : getDiaryWorkerFunctionRepository {

    override suspend fun callDiaryWorkerFunction(): Flow<PagingData<Diary>> = flow {
        val authNumber = getAuthNumber(dao)
        val diaryDate = getCurrentDateString()

        val query = databaseReference.child("users").child(authNumber).child("diary")

        emitAll(
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    prefetchDistance = 20,
                    enablePlaceholders = false,
                    maxSize = 60
                ),
                pagingSourceFactory = { ImagePagingSource(query) }
            ).flow
        )
    }

}

private fun getCurrentDateString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(System.currentTimeMillis())
}

private suspend fun getAuthNumber(dao: UserInfoDao): String {
    return dao.getUserInfo().first().authNumber
}