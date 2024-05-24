package com.company.data.mapper

import com.company.domain.model.DomainBaseProductModel
import com.company.domain.model.DomainProductModel
import com.company.product.model.BaseProductResponse

fun BaseProductResponse.toDomainProductModel(): DomainBaseProductModel {
    return DomainBaseProductModel(
        image = this.image,
        name = this.name
    )
}