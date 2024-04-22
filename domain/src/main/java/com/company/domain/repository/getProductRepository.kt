package com.company.domain.repository

import com.company.domain.model.DomainProductModel
import kotlinx.coroutines.flow.Flow

interface getProductRepository {
    suspend fun getProductList() : Flow<List<DomainProductModel>>
}