package com.company.domain.repository

import com.company.domain.model.DomainProductModel
import kotlinx.coroutines.flow.Flow

interface GetBaseProductRepository {
    suspend fun getBaseProductList()
}