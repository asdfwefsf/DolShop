package com.company.data.worker

import android.util.Log
import com.company.data.datasource.baseproductinfo1.BaseProductDao
import com.company.data.datasource.baseproductinfo1.BaseProductInfo
import com.company.product.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBaseProductWorkerFunction1 @Inject constructor(
    private val productAPI: ProductAPI,
    private val dao: BaseProductDao
) {
    suspend fun getBaseProductList1() {
        val response = productAPI.getBaseProduct1()
        val responseBody = response.body() ?: emptyList()
        if (response.isSuccessful && responseBody != null) {
            withContext(Dispatchers.IO) {
                responseBody.forEach {
                    if (!dao.prodideProductExists(responseBody.size)) {
                        dao.insertBaseProductInfo(
                            BaseProductInfo(
                                image = it.image,
                                name = it.name
                            )

                        )
                        Log.d("daoTest", "insert")
                    } else {
                        dao.updateBaseProductInfo(
                            BaseProductInfo(
                                image = it.image,
                                name = it.name
                            )
                        )
                        Log.d("daoTest", "update")

                    }

                }
            }
        }
    }
}

// BaseProduct는 UI 구성하고 가변 Product는 서버에서 비동기로 데이터 받아와
// 데이터가 받아오기 전의 시간에는 공백이미지로 나타내기