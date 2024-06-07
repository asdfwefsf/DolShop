package com.company.data.mapper

import com.company.data.datasource.productsale.ProductSaleInfo
import com.company.domain.model.DomainProductSaleModel

fun ProductSaleInfo.toDomainProductSaleModel(): DomainProductSaleModel {
    return DomainProductSaleModel(
        saleMunGu = this.saleMunGu,
        couponNumber = this.couponNumber
    )
}