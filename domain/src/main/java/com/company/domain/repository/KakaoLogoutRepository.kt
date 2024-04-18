package com.company.domain.repository

interface KakaoLogoutRepository {
    suspend fun kakaoLogout() : Boolean
}