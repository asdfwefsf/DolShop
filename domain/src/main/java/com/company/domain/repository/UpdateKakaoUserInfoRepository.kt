package com.company.domain.repository

import com.company.domain.model.DomainUserInfoModel
import kotlinx.coroutines.flow.Flow

interface UpdateKakaoUserInfoRepository {
    suspend fun kakaoInfoUpdate() : Flow<DomainUserInfoModel>
}