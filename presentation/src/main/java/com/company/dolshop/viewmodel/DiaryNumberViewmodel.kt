package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.usecase.DiaryNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DiaryNumberViewmodel @Inject constructor(
    private val diaryNumberUseCase: DiaryNumberUseCase
) : ViewModel() {

    private val _diaryNumber = MutableStateFlow<Int?>(null)
    val diaryNumber = _diaryNumber.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getDiaryNumber()

            }
        }
    }
    private fun getDiaryNumber() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val number = diaryNumberUseCase()
                _diaryNumber.value = number
            }
        }
    }


    fun addDiaryNumber() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                diaryNumberUseCase.addOneDiaryNumber()
                getDiaryNumber()
            }

        }
    }


    fun insertDiaryNumber() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                diaryNumberUseCase.insertDiaryNumber()
            }

        }
    }

}