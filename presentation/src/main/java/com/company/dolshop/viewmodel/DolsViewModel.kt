package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.company.domain.entity.Diary
import com.company.domain.repository.getDiaryWorkerFunctionRepository
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
    private val getDiaryWorkerFunction: getDiaryWorkerFunctionRepository

) : ViewModel() {

    private val _diaryda = MutableStateFlow<PagingData<Diary>>(PagingData.empty())
    val diaryda : MutableStateFlow<PagingData<Diary>> = _diaryda


//    val diaryda: Flow<PagingData<Diary>> = getDiaryWorkerFunction.callDiaryWorkerFunction().cachedIn(viewModelScope)


    init {
        viewModelScope.launch {
            getDiaryWorkerFunction.callDiaryWorkerFunction().collect {
                _diaryda.value = it
            }
        }
    }
}
