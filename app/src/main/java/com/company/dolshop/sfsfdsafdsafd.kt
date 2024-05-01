package com.company.dolshop

import com.company.data.datasource.baseproductinfo1.BaseProductInfo

// core 모듈 또는 common 모듈
interface ProductSaleRemoteDataSource {
    fun getData(): BaseProductInfo
}
interface ProductSaleLocalDataSource {
    fun saveData(data : BaseProductInfo)
}

// RemoteDataSource.kt (data module)
class RemoteDataSource : ProductSaleRemoteDataSource {
    override fun getData(): BaseProductInfo {
        TODO("Not yet implemented")
    }
}
// LocalDataSource.kt (data module)
class LocalDataSource : ProductSaleLocalDataSource {
    override fun saveData(data : BaseProductInfo) {
        TODO("Not yet implemented")
    }
}
// domain 모듈
class ProductSaleRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun result() {
        val sali = remoteDataSource.getData()
        localDataSource.saveData(sali)
    }
}