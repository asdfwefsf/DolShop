package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.dolshop.counter.ImageCounter
import com.company.domain.model.DomainProductModel
import com.company.domain.usecase.GetBaseProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetBaseProductViewModel @Inject constructor(
    private val GetBaseProductUseCase : GetBaseProductUseCase,
    private val imageCounter: ImageCounter
) : ViewModel(){

    private val _baseProduct = MutableStateFlow<List<DomainProductModel>>(emptyList())
    val Product : MutableStateFlow<List<DomainProductModel>> = _baseProduct

//    suspend fun getBaseProductList() {
//        GetBaseProductUseCase().collect {
//            _baseProduct.value = it
//        }
//    }

//    init {
//        viewModelScope.launch {
//            getBaseProductList()
//        }
//    }


    val page = imageCounter.count
    fun save(pageNum : Int) {
        imageCounter.savePage(pageNum)
    }

}