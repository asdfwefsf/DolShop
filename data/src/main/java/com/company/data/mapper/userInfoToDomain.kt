package com.company.data.mapper

import com.company.data.datasource.userinfo.UserInfo
import com.company.domain.model.DomainUserInfoModel

fun UserInfo.toDomainUserInfoModel(): DomainUserInfoModel {
    return DomainUserInfoModel(
        authNumber = this.authNumber,
        authEmail = this.authEmail,
        authNicName = this.authNicName,
        authProfileImage = this.authProfileImage
    )
}