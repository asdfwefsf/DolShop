package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import com.company.domain.model.DomainProductModel
import com.company.domain.usecase.getProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class getProductViewModel @Inject constructor(
    private val getProductUsecase : getProductUseCase
) : ViewModel() {


    private val _product = MutableStateFlow<List<DomainProductModel>>(emptyList())
    val product: MutableStateFlow<List<DomainProductModel>> = _product

    suspend fun test() {
        getProductUsecase().collect{
            _product.value = it
        }
    }

//    init {
//        viewModelScope.launch {
//            test()
//        }
//    }
}