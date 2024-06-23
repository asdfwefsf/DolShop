package com.company.domain.usecase.address

import com.company.domain.model.DomainAddress
import com.company.domain.model.DomainProductModel
import com.company.domain.repository.GetAddressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAddressUseCase @Inject constructor(
    private val repository : GetAddressRepository
) {
    suspend operator fun  invoke() : Flow<List<DomainAddress>> {
        return repository.updateAddressList()
    }
}