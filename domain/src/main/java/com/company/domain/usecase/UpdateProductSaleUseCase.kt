package com.company.domain.usecase

import com.company.domain.model.DomainProductSaleModel
import com.company.domain.repository.UpdateProductSaleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateProductSaleUseCase @Inject constructor(
    private val productSaleRepository: UpdateProductSaleRepository
){
    suspend operator fun invoke() : Flow<List<DomainProductSaleModel>> {
        return productSaleRepository.updateSaleMunGu()
    }

    suspend fun getCoupon1() : Flow<List<DomainProductSaleModel>> {
        return productSaleRepository.getCoupon1()
    }

    suspend fun getCoupon2() : Flow<List<DomainProductSaleModel>> {
        return productSaleRepository.getCoupon2()
    }
}