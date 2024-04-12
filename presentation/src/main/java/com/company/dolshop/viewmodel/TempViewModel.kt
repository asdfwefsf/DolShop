package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import com.company.domain.model.TempModel
import com.company.domain.repository.TempRepository
import com.company.domain.usecase.TempUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TempViewModel @Inject constructor(
    private val tempUseCase : TempUsecase
) : ViewModel() {
    fun getTempModel() : TempModel {
        return tempUseCase()
    }
}