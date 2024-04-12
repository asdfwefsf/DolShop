package com.company.domain.usecase

import com.company.domain.model.TempModel
import com.company.domain.repository.TempRepository
import javax.inject.Inject

class TempUsecase @Inject constructor (
    private val repository: TempRepository
) {
    operator fun invoke() : TempModel {
        return repository.getTempModel()
    }
}