package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import com.company.domain.model.DomainAnnouncementModel
import com.company.domain.usecase.announcement.AnnouncementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val announcementUseCase: AnnouncementUseCase
) : ViewModel() {

    private val _announcement = MutableStateFlow<List<DomainAnnouncementModel>>(emptyList())
    val announcement : MutableStateFlow<List<DomainAnnouncementModel>> = _announcement
    suspend fun updateAnnouncementList() {
        announcementUseCase().collect {
            _announcement.value = it
        }
    }

}