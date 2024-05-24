package com.company.data.mapper

import com.company.domain.model.DomainProductModel
import com.company.product.model.ProductResponse

fun ProductResponse.toDomainProductModel(): DomainProductModel {
    return DomainProductModel(
        image1 = this.image1,
        image2 = this.image2,
        image3 = this.image3,
        image4 = this.image4,
        image5 = this.image5,
        text1 =  this.text1,
        text2 =  this.text2,
        text3 =  this.text3,
        text4 =  this.text4,
        text5 =  this.text5,

    )
}