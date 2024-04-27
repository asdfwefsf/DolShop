package com.company.domain.usecase

import com.company.domain.model.DomainProductSaleModel
import com.company.domain.repository.ProductSaleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductSaleUseCase @Inject constructor(
    private val productSaleRepository: ProductSaleRepository
){
    operator fun invoke() : Flow<List<DomainProductSaleModel>> {
        return productSaleRepository.getSaleMunGu()
    }
}