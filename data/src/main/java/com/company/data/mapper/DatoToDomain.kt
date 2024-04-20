package com.company.data.mapper

import com.company.data.datasource.userinfo.UserInfo
import com.company.domain.model.UserInfoModel

fun UserInfo.toUserInfoModel(): UserInfoModel {
    return UserInfoModel(
        authNumber = this.authNumber,
        authEmail = this.authEmail,
        authNicName = this.authNicName,
        authProfileImage = this.authProfileImage
    )
}