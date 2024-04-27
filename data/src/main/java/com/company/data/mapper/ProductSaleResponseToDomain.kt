package com.company.data.mapper

import com.company.domain.model.DomainProductSaleModel
import com.company.network.model.ProductSaleResponse

fun ProductSaleResponse.toDomainProductSaleModel(): DomainProductSaleModel {
    return DomainProductSaleModel(
        saleMunGu = this.saleMunGu
    )
}