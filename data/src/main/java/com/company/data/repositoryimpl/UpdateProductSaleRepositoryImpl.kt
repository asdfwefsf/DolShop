package com.company.data.repositoryimpl


import com.company.data.datasource.local.productsale.ProductSaleDao
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.repository.UpdateProductSaleRepository
import com.company.data.mapper.toDomainProductSaleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateProductSaleRepositoryImpl @Inject constructor(
    private val dao: ProductSaleDao
) : UpdateProductSaleRepository {

    override suspend fun updateSaleMunGu(): Flow<List<DomainProductSaleModel>> {
        return dao.getProductSaleInfo().map {
            it.map {

                it.toDomainProductSaleModel()
            }
        }
    }

    override suspend fun getCoupon1(): Flow<List<DomainProductSaleModel>> {
        return dao.getCoupon1().map {
            it.map {
                it.toDomainProductSaleModel()
            }
        }
    }

    override suspend fun getCoupon2(): Flow<List<DomainProductSaleModel>> {
        return dao.getCoupon2().map {
            it.map {
                it.toDomainProductSaleModel()
            }
        }
    }

}