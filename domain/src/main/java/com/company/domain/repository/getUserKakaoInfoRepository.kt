package com.company.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow



interface getUserKakaoInfoRepository {
    suspend fun getUserKakaoInfo(list: MutableStateFlow<List<String>>) : MutableStateFlow<List<String>>
}