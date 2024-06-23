package com.company.domain.repository

import com.company.domain.model.DomainAddress
import com.company.domain.model.DomainProductModel
import kotlinx.coroutines.flow.Flow

interface GetAddressRepository {
    suspend fun updateAddressList()  : Flow<List<DomainAddress>>

}