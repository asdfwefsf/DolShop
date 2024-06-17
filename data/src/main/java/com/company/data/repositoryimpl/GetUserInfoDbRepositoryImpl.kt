package com.company.data.repositoryimpl

import com.company.data.datasource.local.userinfo.UserInfoDao
import com.company.data.mapper.toDomainUserInfoModel
import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.GetUserInfoDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserInfoDbRepositoryImpl @Inject constructor(
    private val dao : UserInfoDao
) : GetUserInfoDbRepository {
    override suspend fun getUserInfo() : Flow<DomainUserInfoModel> {

        return dao.getUserInfo().filterNotNull().map { userInfo ->
            userInfo.toDomainUserInfoModel()
        }
    }
}