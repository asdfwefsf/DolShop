package com.company.domain.usecase.kakao

import com.company.domain.repository.KakaoLoginRepository
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val repository: KakaoLoginRepository
) {
    suspend operator fun invoke() : Boolean {
        return repository.kakaoLogin()
    }
}