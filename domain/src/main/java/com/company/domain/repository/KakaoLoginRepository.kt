package com.company.domain.repository

interface KakaoLoginRepository {
    suspend fun kakaoLogin() : Boolean
}