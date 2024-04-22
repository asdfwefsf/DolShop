package com.company.data.mapper

import com.company.domain.model.DomainProductModel
import com.company.network.model.ProductResponse

fun ProductResponse.toDomainProductModel(): DomainProductModel {
    return DomainProductModel(
        image = this.image,
        name = this.name
    )
}