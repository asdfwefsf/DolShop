package com.company.dolshop.viewmodel.publicdiary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.entity.PublicDiary
import com.company.domain.usecase.publicidary.SavePublicDiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicDiaryViewModel @Inject constructor(
    private val savePublicDiaryUseCase : SavePublicDiaryUseCase
) : ViewModel() {


    fun savePublicDiary(publicDiary : PublicDiary) {
        viewModelScope.launch {
            savePublicDiaryUseCase(publicDiary)
        }
    }

}