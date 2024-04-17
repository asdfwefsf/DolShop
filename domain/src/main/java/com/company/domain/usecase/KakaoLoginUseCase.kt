package com.company.domain.usecase

import com.company.domain.repository.KakaoLoginRepository
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val repository: KakaoLoginRepository
) {
    operator suspend fun invoke() : Boolean {
        return repository.kakaoLogin()
    }
}