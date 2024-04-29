package com.company.domain.usecase.kakao

import com.company.domain.repository.KakaoLogoutRepository
import javax.inject.Inject

class KakaoLogoutUseCase @Inject constructor(
    private val repository: KakaoLogoutRepository
) {
    operator suspend fun invoke() : Boolean  {
        return repository.kakaoLogout()
    }
}