package com.company.dolshop.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.company.domain.usecase.CoroutineWorkerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class CoroutineWorkerViewModel @Inject constructor(
    private val CoroutineWorkerUseCase : CoroutineWorkerUseCase
) : ViewModel() {
    fun test(context : Context) {
        CoroutineWorkerUseCase(context)
    }
}