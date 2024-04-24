package com.company.domain.usecase

import com.company.domain.model.DomainProductModel
import com.company.domain.repository.GetBaseProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBaseProductUseCase @Inject constructor(
    private val repositoy : GetBaseProductRepository
) {
    suspend operator fun  invoke() {
        repositoy.getBaseProductList()
    }
}