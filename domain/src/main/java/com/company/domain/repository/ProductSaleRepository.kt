package com.company.domain.repository

import com.company.domain.model.DomainProductSaleModel
import kotlinx.coroutines.flow.Flow

interface ProductSaleRepository {
    fun getSaleMunGu() : Flow<List<DomainProductSaleModel>>
}