package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.usecase.ProductSaleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductSaleViewModel @Inject constructor(
    private val productSaleUseCase: ProductSaleUseCase
) : ViewModel() {

    private val _mungu = MutableStateFlow<List<DomainProductSaleModel>>(emptyList())
    val mungu: MutableStateFlow<List<DomainProductSaleModel>> = _mungu

    suspend fun updateSaleMunGu() {
        productSaleUseCase().collect { mungudat->
            _mungu.value = mungudat
        }
    }

    init {
        viewModelScope.launch {
            updateSaleMunGu()
        }
    }
}