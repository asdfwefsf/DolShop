package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.entity.Advertisement
import com.company.domain.usecase.announcement.AnnouncementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val announcementUseCase: AnnouncementUseCase
) : ViewModel() {

    private val _announcement = MutableStateFlow<List<Advertisement>>(emptyList())
    val announcement : MutableStateFlow<List<Advertisement>> = _announcement
    suspend fun updateAnnouncementList() {
        announcementUseCase().collect {
            _announcement.value = it
        }
    }

    init {
        viewModelScope.launch {
            updateAnnouncementList()
        }
    }

}