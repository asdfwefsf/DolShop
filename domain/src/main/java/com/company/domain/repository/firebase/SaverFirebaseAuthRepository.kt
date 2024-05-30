package com.company.domain.repository.firebase

import com.company.domain.model.DomainBaesongInfo
import com.company.domain.model.DomainUserInfoModel

interface SaverFirebaseAuthRepository {

    suspend fun saveFirebaseAuth(domainUserInfoModel : DomainUserInfoModel , currentUser : String)
}