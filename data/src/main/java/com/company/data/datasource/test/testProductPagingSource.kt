package com.company.data.datasource.test

// ProductPagingSource.kt
//class ProductPagingSource(
//    private val productAPI: ProductAPI
//) : PagingSource<Int, DomainProductModel>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainProductModel> {
//        try {
//            val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
//            val response = productAPI.getProduct(page = nextPageNumber)
//            val productList = response.body()?.map { it.toDomainProductModel() } ?: emptyList()
//            val prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1
//            val nextKey = if (productList.isEmpty()) null else nextPageNumber + 1
//
//            return LoadResult.Page(
//                data = productList,
//                prevKey = prevKey,
//                nextKey = nextKey
//            )
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, DomainProductModel>): Int? {
//        return state.anchorPosition
//    }
//}
