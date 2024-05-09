package com.company.dolshop.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.company.domain.entity.Diary
import com.company.domain.repository.GetPublicDiaryWorkerFunctionRepository
import com.company.domain.repository.getDiaryWorkerFunctionRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DolsViewModel @Inject constructor(
    private val getDiaryWorkerFunction: getDiaryWorkerFunctionRepository,
    private val getPublicDiaryWorkerFunction : GetPublicDiaryWorkerFunctionRepository
) : ViewModel() {

    // 다이어리
    private val _diaryda = MutableStateFlow<PagingData<Diary>>(PagingData.empty())
    val diaryda : MutableStateFlow<PagingData<Diary>> = _diaryda
    // 다이어리

    // 다이어리 에서 선택할 날짜
    private val _sort = MutableStateFlow<String>("모두")
    val sort: StateFlow<String> = _sort
    fun updateSort(newSort: String) {
        if (_sort.value != newSort) {
            _sort.value = newSort
            viewModelScope.launch {
                getDiaryWorkerFunction.callDiaryWorkerFunction(sort.value).collect {
                    _diaryda.value = it
                    Log.d("DolsViewModel", sort.value)

                }
            }

        }
    }
    suspend fun callDiaryWorkerFunction() {
        viewModelScope.launch {
            getDiaryWorkerFunction.callDiaryWorkerFunction(sort.value).collect {
                _diaryda.value = it
                Log.d("DolsViewModel", sort.value)

            }
        }

    }
    // 다이어리 에서 선택할 날짜


    // 특정 날짜 업데이트
    private val _specificDate = MutableStateFlow<String?>(null)
    val specificDate: StateFlow<String?> = _specificDate
    fun updateSpecificDate(date: String) {
        _specificDate.value = date
        if (_sort.value == "특정날") {
            viewModelScope.launch {
                getDiaryWorkerFunction.callDiaryWorkerFunction(date).collect{
                    _diaryda.value = it
                }
            }
        }
    }

    // public Diary Get Function Logic
    private val _publicDiaryda = MutableStateFlow<PagingData<Diary>>(PagingData.empty())
    val publicDiaryda : MutableStateFlow<PagingData<Diary>> = _publicDiaryda
    suspend fun callPublicDiaryWorkerFunction() {
        viewModelScope.launch {
            getPublicDiaryWorkerFunction.callPublicDiaryWorkerFunction().collect {
                _publicDiaryda.value = it
                Log.d("DolsViewModel", "publicDiaryda")

            }
        }

    }
    // public Diary Get Function Logic

    // 특정 날짜 업데이트
    init {
        viewModelScope.launch {
            callDiaryWorkerFunction()
            callPublicDiaryWorkerFunction()
        }
    }
}
