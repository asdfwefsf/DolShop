package com.company.data.mapper

import com.company.domain.model.DomainProductModel
import com.company.product.model.BaseProductResponse

fun BaseProductResponse.toDomainProductModel(): DomainProductModel {
    return DomainProductModel(
        image = this.image,
        name = this.name
    )
}