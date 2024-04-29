package com.company.saletodomain.mapper

import com.company.datasource_local_productsale.productsale.ProductSaleInfo
import com.company.domain.model.DomainProductSaleModel

fun com.company.datasource_local_productsale.productsale.ProductSaleInfo.toDomainProductSaleModel(): DomainProductSaleModel {
    return DomainProductSaleModel(
        saleMunGu = this.saleMunGu
    )
}