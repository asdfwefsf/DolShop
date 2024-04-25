package com.company.domain.usecase

import com.company.domain.model.DomainProductModel
import com.company.domain.repository.UpdateBaseProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateBaseProductUseCase @Inject constructor(
    private val repositoy : UpdateBaseProductRepository
) {
    suspend operator fun  invoke() : Flow<List<DomainProductModel>> {
        return repositoy.UpdateBaseProductList()
    }
}