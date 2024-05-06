package com.company.data.worker

import androidx.compose.runtime.collectAsState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.Dao
import com.company.data.datasource.userinfo.UserInfoDao
import com.company.data.worker.test.Diary
import com.company.data.worker.test.ImagePagingSource
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GetBaseProductWorkerFunction2 @Inject constructor(
    private val databaseReference: DatabaseReference,
    private val dao : UserInfoDao
) {
    val scope = CoroutineScope(Dispatchers.IO)

    fun getDiarisFlow(): Flow<PagingData<Diary>> = flow {
        val authNumber = getAuthNumber(dao)
        val diaryDate = getCurrentDateString()

        val query = databaseReference.child("users").child(authNumber).child("diary").child(diaryDate)

        emitAll(
            Pager(
                config = PagingConfig(pageSize = 10, enablePlaceholders = false, maxSize = 50),
                pagingSourceFactory = { ImagePagingSource(query) }
            ).flow
        )
    }


    private fun getCurrentDateString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(System.currentTimeMillis())
    }

    private suspend fun getAuthNumber(dao: UserInfoDao): String {
        return dao.getUserInfo().first().authNumber
    }
}

