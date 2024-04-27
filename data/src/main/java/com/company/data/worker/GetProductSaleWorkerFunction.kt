package com.company.data.worker

import android.util.Log
import com.company.data.datasource.baseproductinfo1.BaseProductDao
import com.company.data.datasource.baseproductinfo1.BaseProductInfo
import com.company.data.datasource.productsale.ProductSaleDao
import com.company.data.datasource.productsale.ProductSaleInfo
import com.company.network.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetProductSaleWorkerFunction @Inject constructor(
    private val productAPI: ProductAPI,
    private val dao: ProductSaleDao
) {
    suspend fun getProductSaleInfo() {
        val response = productAPI.getProductSale()
        val responseBody = response.body() ?: emptyList()
        if (response.isSuccessful && responseBody != null) {
            withContext(Dispatchers.IO) {
                responseBody.forEach {
                    if (!dao.prodideProductExists(responseBody.size)) {
                        dao.insertBaseProductInfo(
                            ProductSaleInfo(
                                saleMunGu = it.saleMunGu
                            )
                        )
                        Log.d("daoTest", "productSaleInsert")
                    } else {
                        dao.updateBaseProductInfo(
                            ProductSaleInfo(
                                saleMunGu = it.saleMunGu
                            )
                        )
                        Log.d("daoTest", "productSaleUpdate")

                    }

                }
            }
        }
    }
}
