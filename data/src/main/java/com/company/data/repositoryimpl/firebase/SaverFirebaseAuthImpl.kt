package com.company.data.repositoryimpl.firebase

import com.company.data.datasource.userinfo.UserInfo
import com.company.data.datasource.userinfo.UserInfoDao
import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.firebase.SaverFirebaseAuthRepository
import javax.inject.Inject

class SaverFirebaseAuthImpl @Inject constructor(
    private val dao: UserInfoDao
) : SaverFirebaseAuthRepository {

    override suspend fun saveFirebaseAuth(
        domainUserInfoModel: DomainUserInfoModel,
        currentUser: String
    ) {
        val userInfo = UserInfo(
            0,
            currentUser,
            domainUserInfoModel.authEmail,
            domainUserInfoModel.authNicName,
            "",
            ""
        )

        if (!dao.prodideUserInfoExists(1)) {
            dao.insertUserInfo(
                UserInfo(
                    userInfo.id,
                    userInfo.authNumber,
                    userInfo.authEmail,
                    userInfo.authNicName,
                    userInfo.authProfileImage,
                    " "
                )
            )
        } else {
            dao.updateUserInfo(
                UserInfo(
                    userInfo.id,
                    userInfo.authNumber,
                    userInfo.authEmail,
                    userInfo.authNicName,
                    userInfo.authProfileImage,
                    " "
                )
            )
        }
    }
}