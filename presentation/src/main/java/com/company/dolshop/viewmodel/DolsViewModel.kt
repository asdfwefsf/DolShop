package com.company.dolshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.company.data.worker.GetBaseProductWorkerFunction2
import com.company.data.worker.test.Diary
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DolsViewModel @Inject constructor(
    private val getBaseProductWorkerFunction2: GetBaseProductWorkerFunction2

) : ViewModel() {



    private val query = Firebase.database.reference.child("images").child("tagNumber")
    val diaryda: Flow<PagingData<Diary>> = getBaseProductWorkerFunction2.getImagesFlow().cachedIn(viewModelScope)


}
