package com.company.data.repositoryimpl

import com.company.data.datasource.productsale.ProductSaleDao
import com.company.data.mapper.toDomainProductSaleModel
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.repository.UpdateProductSaleRepository
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
}