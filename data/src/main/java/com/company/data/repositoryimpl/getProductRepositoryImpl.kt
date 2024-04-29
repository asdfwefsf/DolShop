package com.company.data.repositoryimpl

import com.company.data.mapper.toDomainProductModel
import com.company.domain.model.DomainProductModel
import com.company.domain.repository.getProductRepository
import com.company.product.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class getProductRepositoryImpl @Inject constructor(
    private val productAPI: ProductAPI
) : getProductRepository {


    override fun getProductList(): Flow<List<DomainProductModel>> = flow {
        val response = productAPI.getProduct()
        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                emit(responseBody.map { it.toDomainProductModel() })
            }
        } else {
            emit(emptyList<DomainProductModel>())
        }
    }.flowOn(Dispatchers.IO)
}

