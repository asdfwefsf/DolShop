package com.company.domain.repository

import com.company.domain.model.DomainBaseProductModel
import kotlinx.coroutines.flow.Flow

interface UpdateBaseProductRepository {
    suspend fun UpdateBaseProductList()  : Flow<List<DomainBaseProductModel>>
}