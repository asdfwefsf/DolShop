package com.company.data.worker

import android.util.Log
import com.company.data.datasource.baseproductinfo1.BaseProductDao
import com.company.data.datasource.baseproductinfo1.BaseProductInfo
import com.company.network.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBaseProductWorkerFunction2  @Inject constructor(
    private val productAPI: ProductAPI,
    private val dao: BaseProductDao,

)  {
    suspend fun getBaseProductList2() {
//        val response = productAPI.getBaseProduct2()
//        val responseBody = response.body() ?: emptyList()
//        if (response.isSuccessful && responseBody != null) {
//            withContext(Dispatchers.IO) {
//                responseBody.forEach {
//                    if (!dao.prodideProductExists(responseBody.size)) {
//                        dao.insertBaseProductInfo(
//                            BaseProductInfo(
//                                image = it.image,
//                                name = it.name
//                            )
//                        )
//                        Log.d("daoTest", "insert")
//                    } else {
//                        dao.updateBaseProductInfo(
//                            BaseProductInfo(
//                                image = it.image,
//                                name = it.name
//                            )
//                        )
//                        Log.d("daoTest", "update")
//
//                    }
//
//                }
//            }
//        }

    }
}