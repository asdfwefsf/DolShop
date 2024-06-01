package com.company.dolshop.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SinnUpScreen2ViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle
) : ViewModel() {
    private val _name = MutableStateFlow(savedStateHandle["name"] ?: "")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _id = MutableStateFlow(savedStateHandle["id"] ?: "")
    val id = _id.asStateFlow()

    private val _password = MutableStateFlow(savedStateHandle["password"] ?: "")
    val password = _password.asStateFlow()

    private val _kakaoEmail = MutableStateFlow(savedStateHandle["kakaoEmail"] ?: "")
    val kakaoEmail = _kakaoEmail.asStateFlow()

    private val _phoneNumber = MutableStateFlow(savedStateHandle["phoneNumber"] ?: "")
    val phoneNumber = _phoneNumber.asStateFlow()

    fun setName(name: String) {
        _name.value = name
        savedStateHandle["addressName"] = name
    }

//    fun setId(Number: String) {
//        _addressNumber.value = Number
//        savedStateHandle["addressNumber"] = Number
//    }
//
//    fun setAddress(address: String) {
//        _id.value = address
//        savedStateHandle["address"] = address
//    }
//
//    fun setDetailAddressName(detailName: String) {
//        _detailedAddress.value = detailName
//        savedStateHandle["addressDetailName"] = detailName
//    }
//
//    fun setPhoneNumber(number: String) {
//        _phoneNumber.value = number
//        savedStateHandle["phoneNumber"] = number
//    }
}