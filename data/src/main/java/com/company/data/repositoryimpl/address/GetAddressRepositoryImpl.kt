package com.company.data.repositoryimpl.address

import com.company.data.mapper.toDomainAddress
import com.company.data.datasource.address.AddressDao
import com.company.domain.model.DomainAddress
import com.company.domain.repository.GetAddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAddressRepositoryImpl @Inject constructor(
    private val dao : AddressDao
) : GetAddressRepository {
    override suspend fun UpdateAddressList(): Flow<List<DomainAddress>> {
        return dao.getAddressInfo().map {
            it.map {
                it.toDomainAddress()
            }
        }
    }
}