package com.company.domain.repository

import com.company.domain.model.DomainBaseProductModel
import com.company.domain.model.DomainProductModel
import kotlinx.coroutines.flow.Flow

interface getProductRepository {
    fun getProductList() : Flow<List<DomainProductModel>>
}