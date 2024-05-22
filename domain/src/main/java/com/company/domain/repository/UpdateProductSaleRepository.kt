package com.company.domain.repository

import com.company.domain.model.DomainProductSaleModel
import kotlinx.coroutines.flow.Flow

interface UpdateProductSaleRepository {
    suspend fun updateSaleMunGu() : Flow<List<DomainProductSaleModel>>

    suspend fun getCoupon1() : Flow<List<DomainProductSaleModel>>
    suspend fun getCoupon2() : Flow<List<DomainProductSaleModel>>
}