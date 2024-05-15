package com.company.dolshop.viewmodel.publicdiary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.entity.PublicDiary
import com.company.domain.usecase.publicidary.DeletePublicDiaryUseCase
import com.company.domain.usecase.publicidary.GetPublicDiaryUseCase
import com.company.domain.usecase.publicidary.SavePublicDiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicDiaryViewModel @Inject constructor(
    private val savePublicDiaryUseCase: SavePublicDiaryUseCase,
    private val getPublicDiaryUseCase: GetPublicDiaryUseCase,
    private val deletePublicDiaryUseCase: DeletePublicDiaryUseCase
) : ViewModel() {


    fun savePublicDiary(publicDiary: PublicDiary) {
        viewModelScope.launch {
            savePublicDiaryUseCase(publicDiary)
        }
    }

    private val _publicDiary = MutableStateFlow<List<PublicDiary>>(emptyList())
    val publicDiary = _publicDiary

    fun getPublicDiart() {
        viewModelScope.launch {
            getPublicDiaryUseCase().collect {
                _publicDiary.value = it
            }
        }

    }

    init {
        getPublicDiart()
    }

    fun deletePublicDiary(publicDiary : PublicDiary) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePublicDiaryUseCase(publicDiary)
        }
    }

}