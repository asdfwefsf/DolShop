package com.company.data.repositoryimpl

import com.company.data.datasource.productsale.ProductSaleDao
import com.company.data.mapper.toDomainAnnouncementModel
import com.company.data.mapper.toDomainProductModel
import com.company.data.mapper.toDomainProductSaleModel
import com.company.domain.model.DomainAnnouncementModel
import com.company.domain.model.DomainProductModel
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.repository.ProductSaleRepository
import com.company.network.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateProductSaleRepositoryImpl @Inject constructor(
    private val dao: ProductSaleDao
) : ProductSaleRepository {

    override suspend fun getSaleMunGu(): Flow<List<DomainProductSaleModel>> {
        return dao.getBaseProductInfo().map {
            it.map {
                it.toDomainProductSaleModel()
            }
        }
    }
}