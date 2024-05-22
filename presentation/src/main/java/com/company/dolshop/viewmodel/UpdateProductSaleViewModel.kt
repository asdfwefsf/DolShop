package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.usecase.UpdateProductSaleUseCase
import com.company.utility.DataStoreUtility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateProductSaleViewModel @Inject constructor(
    private val productSaleUseCase: UpdateProductSaleUseCase,
) : ViewModel() {

    private val _mungu = MutableStateFlow<List<DomainProductSaleModel>>(emptyList())
    val mungu: MutableStateFlow<List<DomainProductSaleModel>> = _mungu

    suspend fun updateSaleMunGu() {
        productSaleUseCase().collect {
            _mungu.value = it
        }
    }

    init {
        viewModelScope.launch {
            updateSaleMunGu()
        }
    }



    val dataStoreUtility = DataStoreUtility.getInstance()
//    val isCoupon1 by dataStoreUtility.run { contetx.isCoupon1Flow.collectAsStateWithLifecycle(initialValue = false) }
//    val isCoupon2 by dataStoreUtility.run { contetx.isCoupon2Flow.collectAsStateWithLifecycle(initialValue = false) }
    private val _savedSaleCoupon = MutableStateFlow<List<DomainProductSaleModel>>(emptyList())
    val savedSaleCoupon: MutableStateFlow<List<DomainProductSaleModel>> = _savedSaleCoupon

    suspend fun updateSavedSaleCoupon() {
        productSaleUseCase().collect {
            _savedSaleCoupon.value = it
        }
    }


}