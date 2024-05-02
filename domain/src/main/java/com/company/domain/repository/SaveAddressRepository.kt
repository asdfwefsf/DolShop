package com.company.domain.repository

import com.company.domain.model.DomainAddress

interface SaveAddressRepository  {
    suspend fun saveAddressInfo(domainAddress: DomainAddress)
}