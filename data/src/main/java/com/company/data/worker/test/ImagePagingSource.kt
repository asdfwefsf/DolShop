package com.company.data.worker.test

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.company.domain.entity.Diary
import com.google.firebase.database.Query
import kotlinx.coroutines.tasks.await

class ImagePagingSource(
    private val query: Query
) : PagingSource<Query, Diary>() {

    override suspend fun load(params: LoadParams<Query>): LoadResult<Query, Diary> {
        return try {

            val dataSnapshot = params.key?.get()?.await() ?: query.limitToFirst(params.loadSize).get().await()

            val diarys = dataSnapshot.children.mapNotNull { dataSnapshot ->
                val diary = dataSnapshot.child("diary").getValue(String::class.java)
                val image  = dataSnapshot.child("image").getValue(String::class.java)
                val day = dataSnapshot.child("day").getValue(String::class.java)
                val diaryNumber = dataSnapshot.child("diaryNumber").getValue(String::class.java)
                if (diary != null && image != null && day != null && diaryNumber != null) Diary(diary, image , day , diaryNumber) else null

            }


            val lastItemKey = dataSnapshot.children.lastOrNull()?.key
            val nextQuery = if (diarys.size == params.loadSize && lastItemKey != null) {
                query.startAfter(lastItemKey).limitToFirst(params.loadSize)
            } else null

            LoadResult.Page(
                data = diarys,
                prevKey = null, // 스크롤 업 시 필요한 경우 이전 키 설정
                nextKey = nextQuery
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Query, Diary>): Query? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { item ->
                query.startAt(item.diary).limitToFirst(state.config.pageSize)
                query.startAt(item.image).limitToFirst(state.config.pageSize)
                query.startAt(item.day).limitToFirst(state.config.pageSize)
            }
        }
    }
}

//data class Diary(
//    val diary: String,
//    val image: String,
//    val day : String,
//    val diaryNumber : String
//)