package com.company.data.worker

import android.util.Log
import com.company.datasource_local_productsale.productsale.ProductSaleDao
import com.company.datasource_local_productsale.productsale.ProductSaleInfo
import com.company.product.api.ProductAPI
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
                    if (!dao.prodideProductSaleInfoExists(responseBody.size)) {
                        dao.insertProductSaleInfo(
                            ProductSaleInfo(
                                saleMunGu = it.saleMunGu,
                                couponNumber = it.couponNumber
                            )
                        )
                        Log.d("daoTest", "productSaleInsert")
                    } else {
                        dao.updateProductSaleInfo(
                            ProductSaleInfo(
                                saleMunGu = it.saleMunGu,
                                couponNumber = it.couponNumber
                            )
                        )
                        Log.d("daoTest", "productSaleUpdate")

                    }

                }
            }
        }
    }
}
