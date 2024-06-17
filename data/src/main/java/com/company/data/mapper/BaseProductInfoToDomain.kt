package com.company.data.mapper

import com.company.data.datasource.local.baseproductinfo1.BaseProductInfo
import com.company.domain.model.DomainBaseProductModel
import com.company.domain.model.DomainProductModel

fun BaseProductInfo.toDomainBaseProductModel(): DomainBaseProductModel {
    return DomainBaseProductModel(
        image = this.image,
        name = this.name
    )
}


//fun BaseProductInfo.toDomainProductModel(): DomainProductModel {
//    return DomainProductModel(
//        image = this.image,
//        name = this.name
//    )
//}