package com.company.data.worker.test

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.company.domain.entity.Diary
import com.google.firebase.database.Query
import kotlinx.coroutines.tasks.await

// 모두
class ImagePagingSource2(
    private val query: Query
) : PagingSource<String, Diary>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Diary> {
        val dataSnapshot = (params.key?.let { query.startAfter(it).limitToFirst(params.loadSize) } ?: query.limitToFirst(params.loadSize)).get().await()
        val diaries = mutableListOf<Diary>()

        dataSnapshot.children.forEach { dateSnapshot ->
            dateSnapshot.children.forEach { diarySnapshot ->
                diarySnapshot.getValue(Diary::class.java)?.let {
                    diaries.add(it)
                }
            }
        }

        val lastItemKey = dataSnapshot.children.lastOrNull()?.key

        return LoadResult.Page(
            data = diaries.reversed(),
            prevKey = null,
            nextKey = lastItemKey
        )
    }


    override fun getRefreshKey(state: PagingState<String, Diary>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
