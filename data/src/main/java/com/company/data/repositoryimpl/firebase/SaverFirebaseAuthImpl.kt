package com.company.data.repositoryimpl.firebase

import android.util.Log
import com.company.data.datasource.local.userinfo.UserInfo
import com.company.data.datasource.local.userinfo.UserInfoDao
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
            domainUserInfoModel.authNickName,
            "",
            ""
        )

        if (!dao.prodideUserInfoExists(1)) {
            Log.d("1d5e1f0" , dao.prodideUserInfoExists(1).toString())
            dao.insertUserInfo(
                UserInfo(
                    userInfo.id,
                    userInfo.authNumber,
                    userInfo.authEmail,
                    userInfo.authNickName,
                    userInfo.authProfileImage,
                    " "
                )
            )
        } else {
            dao.updateUserInfo(
                UserInfo(
                    1,
                    userInfo.authNumber,
                    userInfo.authEmail,
                    userInfo.authNickName,
                    userInfo.authProfileImage,
                    " "
                )
            )
        }
    }
}