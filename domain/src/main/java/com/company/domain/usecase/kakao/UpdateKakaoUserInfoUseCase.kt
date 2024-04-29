package com.company.domain.usecase.kakao

import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.UpdateKakaoUserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateKakaoUserInfoUseCase @Inject constructor(
    private val repository : UpdateKakaoUserInfoRepository
){
    operator suspend fun invoke() : Flow<DomainUserInfoModel> {
        return repository.kakaoInfoUpdate()
    }
}