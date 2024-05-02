package com.company.dolshop.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.domain.model.DomainAddress
import com.company.domain.usecase.address.SaveAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val saveAddressUseCase: SaveAddressUseCase
) : ViewModel() {


    private val _addressName = MutableStateFlow(savedStateHandle["addressName"] ?: "")
    val addressName: StateFlow<String> = _addressName.asStateFlow()

    private val _addressNumber = MutableStateFlow(savedStateHandle["addressNumber"] ?: "")
    val addressNumber = _addressNumber.asStateFlow()

    private val _address = MutableStateFlow(savedStateHandle["address"] ?: "")
    val address = _address.asStateFlow()

    private val _detailedAddress = MutableStateFlow(savedStateHandle["addressDetailName"] ?: "")
    val detailedAddress = _detailedAddress.asStateFlow()

    private val _phoneNumber = MutableStateFlow(savedStateHandle["phoneNumber"] ?: "")
    val phoneNumber = _phoneNumber.asStateFlow()

    fun setAddressName(name: String) {
        _addressName.value = name
        savedStateHandle["addressName"] = name
    }

    fun setAddressNumber(Number: String) {
        _addressNumber.value = Number
        savedStateHandle["addressNumber"] = Number
    }

    fun setAddress(address: String) {
        _address.value = address
        savedStateHandle["address"] = address
    }

    fun setDetailAddressName(detailName: String) {
        _detailedAddress.value = detailName
        savedStateHandle["addressDetailName"] = detailName
    }

    fun setPhoneNumber(number: String) {
        _phoneNumber.value = number
        savedStateHandle["phoneNumber"] = number
    }

    fun saveAddress(domainAddress: DomainAddress) {
        viewModelScope.launch {
            saveAddressUseCase(domainAddress)
        }
    }


    //    init {
//        savedStateHandle.get<String>("addressName")?.let {
//            setAddressName(it)
//        }
//    }
}
