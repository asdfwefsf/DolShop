package com.company.dolshop.counter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageCounter @Inject constructor() {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun savePage(pageNum : Int) {
        _count.value = pageNum
    }
}
