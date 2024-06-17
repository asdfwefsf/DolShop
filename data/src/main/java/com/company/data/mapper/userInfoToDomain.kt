package com.company.data.mapper

import com.company.data.datasource.local.userinfo.UserInfo
import com.company.domain.model.DomainUserInfoModel

fun UserInfo.toDomainUserInfoModel(): DomainUserInfoModel {
    return DomainUserInfoModel(
        authNumber = this.authNumber,
        authEmail = this.authEmail,
        authNickName = this.authNickName,
        authProfileImage = this.authProfileImage
    )
}