package com.company.domain.repository

import com.company.domain.model.DomainUserInfoModel
import kotlinx.coroutines.flow.Flow

interface GetUserInfoDbRepository {
    suspend fun getUserInfo() : Flow<DomainUserInfoModel>
}