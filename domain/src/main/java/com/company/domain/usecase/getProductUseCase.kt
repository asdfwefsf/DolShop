package com.company.domain.usecase

import com.company.domain.model.DomainBaseProductModel
import com.company.domain.model.DomainProductModel
import com.company.domain.repository.getProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getProductUseCase @Inject constructor(
    private val repository : getProductRepository
) {
    operator fun invoke() : Flow<List<DomainProductModel>> {
        return repository.getProductList()
    }
}