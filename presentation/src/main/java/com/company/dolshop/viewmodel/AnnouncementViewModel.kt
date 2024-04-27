package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import com.company.domain.usecase.AnnouncementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AnnouncementViewModel(
    private val announcementUseCase: AnnouncementUseCase
) : ViewModel() {

}