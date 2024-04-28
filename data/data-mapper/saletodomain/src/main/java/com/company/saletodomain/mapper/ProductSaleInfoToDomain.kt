package com.company.saletodomain.mapper

import com.company.data_datasource.productsale.ProductSaleInfo
import com.company.domain.model.DomainProductSaleModel

fun ProductSaleInfo.toDomainProductSaleModel(): DomainProductSaleModel {
    return DomainProductSaleModel(
        saleMunGu = this.saleMunGu
    )
}