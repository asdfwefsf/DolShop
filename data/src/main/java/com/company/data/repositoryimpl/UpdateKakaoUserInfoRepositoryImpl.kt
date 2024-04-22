package com.company.data.repositoryimpl

import com.company.data.datasource.userinfo.UserInfoDao
import com.company.data.mapper.toDomainUserInfoModel
import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.UpdateKakaoUserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateKakaoUserInfoRepositoryImpl @Inject constructor(
    private val dao : UserInfoDao
) : UpdateKakaoUserInfoRepository{
    override suspend fun kakaoInfoUpdate() : Flow<DomainUserInfoModel> {

        return dao.getUserInfo().filterNotNull().map { userInfo ->
            userInfo.toDomainUserInfoModel()
        }
    }
}