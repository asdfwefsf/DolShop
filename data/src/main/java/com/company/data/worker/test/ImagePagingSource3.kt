package com.company.data.worker.test

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.company.domain.entity.Diary
import com.company.domain.entity.PublicDiary
import com.google.firebase.database.Query
import kotlinx.coroutines.tasks.await

class ImagePagingSource3(
    private val query: Query
) : PagingSource<String, PublicDiary>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PublicDiary> {
        val dataSnapshot = (params.key?.let { query.startAfter(it).limitToFirst(params.loadSize) } ?: query.limitToFirst(params.loadSize)).get().await()
        val diaries = mutableListOf<PublicDiary>()
        // query = publicDiary부터 시작
        dataSnapshot.children.forEach { usersSnapshot ->
            usersSnapshot.children.forEach {detailUserSnapshot ->
                detailUserSnapshot.child("images").getValue(PublicDiary::class.java)?.let{
                    diaries.add(it)
                }

//                detailUserSnapshot.child("images").children.forEach { imageSnapshot ->
//                    imageSnapshot.children.forEach { detailImageSnapshot ->
//                        detailImageSnapshot.getValue(PublicDiary::class.java)?.let {
//                            diaries.add(it)
//                        }
//
//                    }
//                }
            }


        }
//        val lastItemKey = dataSnapshot.children.lastOrNull()?.children?.lastOrNull()?.key

        val lastItemKey = dataSnapshot.children.lastOrNull()?.key

        return LoadResult.Page(
            data = diaries.reversed(),
            prevKey = null,
            nextKey = lastItemKey
        )
    }

    override fun getRefreshKey(state: PagingState<String, PublicDiary>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
