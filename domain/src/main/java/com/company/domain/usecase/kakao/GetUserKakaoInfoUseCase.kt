package com.company.domain.usecase.kakao

import com.company.domain.repository.getUserKakaoInfoRepository
import javax.inject.Inject

class GetUserKakaoInfoUseCase @Inject constructor(
    private val repository : getUserKakaoInfoRepository
) {
    suspend operator fun invoke() {
        repository.getUserKakaoInfo()
    }
}