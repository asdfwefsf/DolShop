package com.company.data.mapper

import com.company.data.datasource.baseproductinfo.BaseProductInfo
import com.company.data.datasource.userinfo.UserInfo
import com.company.domain.model.DomainProductModel
import com.company.domain.model.DomainUserInfoModel

fun BaseProductInfo.toDomainProductModel(): DomainProductModel {
    return DomainProductModel(
        image = this.image,
        name = this.name
    )
}