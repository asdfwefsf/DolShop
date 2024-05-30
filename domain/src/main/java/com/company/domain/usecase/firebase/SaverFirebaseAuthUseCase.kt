package com.company.domain.usecase.firebase

import com.company.domain.model.DomainUserInfoModel
import com.company.domain.repository.firebase.SaverFirebaseAuthRepository
import javax.inject.Inject

class SaverFirebaseAuthUseCase @Inject constructor(
    private val repository : SaverFirebaseAuthRepository
){

//    var domainUserInfoModel = DomainUserInfoModel(" " , " " , " " , " ")

    suspend operator fun invoke(domainUserInfoModel : DomainUserInfoModel , currentUser : String) {
        repository.saveFirebaseAuth(domainUserInfoModel , currentUser)
    }

}