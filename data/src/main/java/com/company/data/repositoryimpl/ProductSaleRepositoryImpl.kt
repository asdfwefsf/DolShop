package com.company.data.repositoryimpl

import com.company.data.mapper.toDomainAnnouncementModel
import com.company.data.mapper.toDomainProductSaleModel
import com.company.domain.model.DomainAnnouncementModel
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.repository.ProductSaleRepository
import com.company.network.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductSaleRepositoryImpl @Inject constructor(
    private val productAPI: ProductAPI
) : ProductSaleRepository{


    override fun getSaleMunGu() : Flow<List<DomainProductSaleModel>> = flow {
        val response = productAPI.getProductSale()
        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                emit(responseBody.map { it.toDomainProductSaleModel() })
            }
        } else {
            emit(emptyList<DomainProductSaleModel>())
        }
    }.flowOn(Dispatchers.IO)
}