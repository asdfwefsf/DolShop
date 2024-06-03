package com.company.domain.usecase.kakao

import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.GetUserInfoDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoDbUseCase @Inject constructor(
    private val repository : GetUserInfoDbRepository
){
    operator suspend fun invoke() : Flow<DomainUserInfoModel> {
        return repository.getUserInfo()
    }
}