package com.company.data.repositoryimpl

import android.util.Log
import com.company.data.datasource.baseproductinfo.BaseProductDao
import com.company.data.datasource.baseproductinfo.BaseProductInfo
import com.company.data.mapper.toDomainProductModel
import com.company.domain.model.DomainProductModel
import com.company.domain.repository.GetBaseProductRepository
import com.company.domain.repository.getProductRepository
import com.company.network.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBaseProductRespositoryImpl @Inject constructor(
    private val productAPI: ProductAPI,
    private val dao : BaseProductDao
) : GetBaseProductRepository {
    override suspend fun getBaseProductList() {
        val response = productAPI.getBaseProduct()
        val responseBody = response.body() ?: emptyList()
        if (response.isSuccessful && responseBody != null) {
            withContext(Dispatchers.IO) {
                responseBody.forEach {
                    dao.upsertBaseProductInfo(
                        BaseProductInfo(
                            image = it.image,
                            name = it.name
                        )
                    )
                }
            }
    }
}

//
//override suspend fun getImage() {
//    val result = imageLinkAPI.getImages()
//    val resultBody = result.body() ?: emptyList()
//    if (result.isSuccessful && result.body() != null) {
//        withContext(Dispatchers.IO) {
//            resultBody.forEach {
//                dao.upsertImageLink(
//                    ImageLink(imageLink = it)
//                )
//            }
//        }
//    }
//}
}

// BaseProduct는 UI 구성하고 가변 Product는 서버에서 비동기로 데이터 받아와
// 데이터가 받아오기 전의 시간에는 공백이미지로 나타내기