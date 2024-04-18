package com.company.domain.usecase

import com.company.domain.repository.getUserKakaoInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class getUserKakaoInfoUseCase @Inject constructor(
    private val repository : getUserKakaoInfoRepository
){
    suspend operator fun invoke(list: MutableStateFlow<List<String>>) {
        repository.getUserKakaoInfo(list)
    }
}