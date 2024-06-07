package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.DomainProductSaleModel
import com.company.domain.usecase.UpdateProductSaleUseCase
import com.company.utility.datastore.DataStoreUtility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
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
            coroutineScope {
                launch { updateSaleMunGu() }
                launch { updateSavedSaleCoupon1() }
                launch { updateSavedSaleCoupon2() }
            }


//            if(savedSaleCoupon1Boolean.value) {
//            }
//            if(savedSaleCoupon2Boolean.value) {
//            }
        }
    }


    val dataStoreUtility = DataStoreUtility.getInstance()
//    val isCoupon1 by dataStoreUtility.run { contetx.isCoupon1Flow.collectAsStateWithLifecycle(initialValue = false) }
//    val isCoupon2 by dataStoreUtility.run { contetx.isCoupon2Flow.collectAsStateWithLifecycle(initialValue = false) }

    // 쿠폰1
    private val _savedSaleCoupon1 = MutableStateFlow<List<DomainProductSaleModel>>(emptyList())
    val savedSaleCoupon1: MutableStateFlow<List<DomainProductSaleModel>> = _savedSaleCoupon1

//    private val _savedSaleCoupon1Boolean = MutableStateFlow<Boolean>(false)
//    val savedSaleCoupon1Boolean: MutableStateFlow<Boolean> = _savedSaleCoupon1Boolean
//    fun setSavedSaleCoupon1Boolean(value: Boolean) {
//        _savedSaleCoupon1Boolean.value = value
//    }

    suspend fun updateSavedSaleCoupon1() {
        productSaleUseCase.getCoupon1().collect {
            _savedSaleCoupon1.value = it
        }
    }

    // 쿠폰2
    private val _savedSaleCoupon2 = MutableStateFlow<List<DomainProductSaleModel>>(emptyList())
    val savedSaleCoupon2: MutableStateFlow<List<DomainProductSaleModel>> = _savedSaleCoupon2


//    private val _savedSaleCoupon2Boolean = MutableStateFlow<Boolean>(false)
//    val savedSaleCoupon2Boolean: MutableStateFlow<Boolean> = _savedSaleCoupon2Boolean
//    fun setSavedSaleCoupon2Boolean(value: Boolean) {
//        _savedSaleCoupon2Boolean.value = value
//    }

    suspend fun updateSavedSaleCoupon2() {
        productSaleUseCase.getCoupon2().collect {
            _savedSaleCoupon2.value = it
        }


    }

}