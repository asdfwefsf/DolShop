package com.company.domain.usecase

import com.company.domain.model.UserInfoModel
import com.company.domain.repository.UpdateKakaoUserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateKakaoUserInfoUseCase @Inject constructor(
    private val repository : UpdateKakaoUserInfoRepository
){
    operator suspend fun invoke() : Flow<UserInfoModel> {
        return repository.kakaoInfoUpdate()
    }
}