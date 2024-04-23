package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import com.company.domain.usecase.GetBaseProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetBaseProductViewModel @Inject constructor(
    private val GetBaseProductUseCase : GetBaseProductUseCase
) : ViewModel(){

}