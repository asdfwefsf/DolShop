package com.company.data.worker.test

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.database.Query
import kotlinx.coroutines.tasks.await

//class ImagePagingSource(
//    private val query: Query
//) : PagingSource<Query, String>() {
//
//    override suspend fun load(params: LoadParams<Query>): LoadResult<Query, String> {
//        return try {
//            val dataSnapshot = params.key?.get()?.await() ?: query.limitToFirst(params.loadSize).get().await()
//            val urls = dataSnapshot.children.mapNotNull { it.getValue(String::class.java) }
//
//            // 마지막 아이템의 키 값을 획득
//            val lastItemKey = dataSnapshot.children.lastOrNull()?.key
//
//            val nextQuery = if (urls.size == params.loadSize && lastItemKey != null) {
//                query.startAfter(lastItemKey).limitToFirst(params.loadSize)
//            } else null
//
//            LoadResult.Page(
//                data = urls,
//                prevKey = null,
//                nextKey = nextQuery
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//
//    override fun getRefreshKey(state: PagingState<Query, String>): Query? {
//        return null
//    }
//}
class ImagePagingSource(
    private val query: Query
) : PagingSource<Query, String>() {

    override suspend fun load(params: LoadParams<Query>): LoadResult<Query, String> {
        return try {
            val dataSnapshot = params.key?.get()?.await() ?: query.limitToFirst(params.loadSize).get().await()
            val urls = dataSnapshot.children.mapNotNull { it.getValue(String::class.java) }
            val lastItemKey = dataSnapshot.children.lastOrNull()?.key
            val nextQuery = if (urls.size == params.loadSize && lastItemKey != null) {
                query.startAfter(lastItemKey).limitToFirst(params.loadSize)
            } else null

            LoadResult.Page(
                data = urls,
                prevKey = null, // 스크롤 업 시 필요한 경우 이전 키 설정
                nextKey = nextQuery
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Query, String>): Query? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { item ->
                query.startAt(item).limitToFirst(state.config.pageSize)
            }
        }
    }
}