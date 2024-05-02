package com.company.domain.usecase.address

import com.company.domain.model.DomainAddress
import com.company.domain.repository.SaveAddressRepository
import javax.inject.Inject

class SaveAddressUseCase @Inject constructor(
    private val saveAddressRepository: SaveAddressRepository
){
    suspend operator fun invoke(domainAddress: DomainAddress) {
        saveAddressRepository.saveAddressInfo(domainAddress)
    }
}