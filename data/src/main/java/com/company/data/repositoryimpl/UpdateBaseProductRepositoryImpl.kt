package com.company.data.repositoryimpl

import com.company.data.datasource.local.baseproductinfo1.BaseProductDao
import com.company.data.mapper.toDomainBaseProductModel
import com.company.domain.model.DomainBaseProductModel
import com.company.domain.repository.UpdateBaseProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateBaseProductRepositoryImpl @Inject constructor(
    private val dao : BaseProductDao
) : UpdateBaseProductRepository {
    override suspend fun UpdateBaseProductList() : Flow<List<DomainBaseProductModel>> {
        return dao.getBaseProductInfo().map {
            it.map {
                it.toDomainBaseProductModel()
            }
        }
    }
}