package com.company.data.mapper

import com.company.data.datasource.baseproductinfo1.BaseProductInfo
import com.company.domain.model.DomainProductModel

fun BaseProductInfo.toDomainProductModel(): DomainProductModel {
    return DomainProductModel(
        image = this.image,
        name = this.name
    )
}