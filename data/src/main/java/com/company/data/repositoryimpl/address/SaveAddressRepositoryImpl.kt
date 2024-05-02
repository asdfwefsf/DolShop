package com.company.data.repositoryimpl.address

import com.company.data.mapper.toAddressInfo
import com.company.datasource_local_address.AddressDao
import com.company.domain.model.DomainAddress
import com.company.domain.repository.SaveAddressRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveAddressRepositoryImpl @Inject constructor(
    private val dao : AddressDao
) : SaveAddressRepository{
    override suspend fun saveAddressInfo(domainAddress: DomainAddress) {
        val mapperSucced = domainAddress.toAddressInfo()
        withContext(Dispatchers.IO) {
            dao.insertAddressInfo(mapperSucced)

        }
    }

}