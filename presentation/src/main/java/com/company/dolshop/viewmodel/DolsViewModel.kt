package com.company.dolshop.viewmodel

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.company.domain.entity.Diary
import com.company.domain.entity.PublicDiary
import com.company.domain.repository.GetPublicDiaryWorkerFunctionRepository
import com.company.domain.repository.getDiaryWorkerFunctionRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class DolsViewModel @Inject constructor(
    private val getDiaryWorkerFunction: getDiaryWorkerFunctionRepository,
    private val getPublicDiaryWorkerFunction: GetPublicDiaryWorkerFunctionRepository
) : ViewModel() {

    // 다이어리
    private val _diaryda = MutableStateFlow<PagingData<Diary>>(PagingData.empty())
    val diaryda: MutableStateFlow<PagingData<Diary>> = _diaryda
    // 다이어리

//    // 퍼블릭 다이어리
//    private val _publicDiaryda = MutableStateFlow<PagingData<PublicDiary>>(PagingData.empty())
//    val publicDiaryda: MutableStateFlow<PagingData<PublicDiary>> = _publicDiaryda
//    // 퍼블릭 다이어리

    // 다이어리 에서 선택할 날짜
    private val _sort = MutableStateFlow<String>("모두")
    val sort: StateFlow<String> = _sort
    fun updateSort(newSort: String) {
        if (_sort.value != newSort) {
            _sort.value = newSort
            viewModelScope.launch {
                getDiaryWorkerFunction.callDiaryWorkerFunction(sort.value).collect {
                    _diaryda.value = it
                    Log.d("DolsViewModel", sort.value)

                }
            }

        }
    }

    suspend fun callDiaryWorkerFunction(sort: String) {
        viewModelScope.launch {
            getDiaryWorkerFunction.callDiaryWorkerFunction(sort).collect {
                _diaryda.value = it
//                Log.d("DolsViewModel", sort.value)

            }
        }

    }
    // 다이어리 에서 선택할 날짜


    // 특정 날짜 업데이트
    private val _specificDate = MutableStateFlow<String?>(null)
    val specificDate: StateFlow<String?> = _specificDate
    fun updateSpecificDate(date: String) {
        _specificDate.value = date
        if (_sort.value == "특정날") {
            viewModelScope.launch {
                getDiaryWorkerFunction.callDiaryWorkerFunction(date).collect {
                    _diaryda.value = it
                }
            }
        }
    }
    // 특정 날짜 업데이트


    // 퍼블릭 다이어리
    private val _publicDiaryda = MutableStateFlow<PagingData<PublicDiary>>(PagingData.empty())
    val publicDiaryda: MutableStateFlow<PagingData<PublicDiary>> = _publicDiaryda

    @WorkerThread
    suspend fun callPublicDiaryWorkerFunction() {
        getPublicDiaryWorkerFunction.callPublicDiaryWorkerFunction().collect {
            _publicDiaryda.value = it
        }
    }
    // 퍼블릭 다이어리

    // 좋아요
    // 호출 시점 : "Community Screen" -> "DetailDialog Screen" 좋아요 버튼 누르면 호출
    // 함수 기능 : 파이어베이스에 저장된 해당 퍼블릭 다이어리에 대한 나의 좋아요 여부에 따른 좋아요 정보 수정
    //            케이스1 : 내가 좋아요 누른 상태에서 좋아요 버튼 누르면 해당 퍼블릭 다이어리 내부에서 내 좋아요 삭제
    //            케이스2 : 내가 좋아요 누르지 않은 상태에서 좋아요 버튼 누르면 해당 퍼블릭 다이어리 내부애서 내 좋아요 추가
    fun setJoyaoToFirebase(imageId: String, userId: String , publicDiary: PublicDiary) {
        val db = Firebase.database.reference
        val diaryRef = db.child("publicDiary")
//        addValueEventListener
        diaryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("hahaha", "1 : ${imageId}")
                Log.d("hahaha", "1 : ${snapshot}")
                //
                snapshot.children.forEach { userSnapShot ->
                    Log.d("hahaha", "2 : ${userSnapShot}")

                    userSnapShot.children.forEach { ImagesSnapShot ->
                        Log.d("hahaha", "3 : ${ImagesSnapShot}")

                        val diary = ImagesSnapShot.child("images").getValue(PublicDiary::class.java)

                        Log.d("hahaha", "4 : ${ImagesSnapShot}")
                        Log.d("hahaha", "5 : ${diary}")

                        if (diary?.image == imageId) {
                            Log.d("hahaha", "6 : ${imageId}")

                            val joayoRef = ImagesSnapShot.ref.child("joayo")

                            val myid = joayoRef.child(userId)

                            myid.get().addOnSuccessListener { dataSnapshot ->
                                if (dataSnapshot.exists()) {
                                    joayoRef.removeValue()
                                    negativeJoayoUiChange()
//                                    positiveJoayoUiChange(publicDiary)
                                } else {
                                    joayoRef.child(userId).setValue(true)
                                    positiveJoayoUiChange()
//                                    negativeJoayoUiChange(publicDiary)
                                }
                            }.addOnFailureListener {
                            }
                            joayoRef.child(userId).setValue(true)

                        }
                    }
                }
                //

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
    // 좋아요

    // 호출 시점 : "Community Screen" -> "PublicDiarys"에서 특정한 퍼블리 다이어리 클릭하면 호출
    // 함수 기능 : 파이어베이스에서 해당하는 퍼블릭 다이어리의 좋아요 갯수와 , 사용자가 해당 퍼블릭 다이어리에 좋아요 눌렀는지 여부를
    //           가져와서 반환한다.
//    suspend fun getJoyaoFromFirebase(authNumber: String, imageNumber: String): MutableList<Pair<String, Boolean>> {
    suspend fun getJoyaoFromFirebase(authNumber: String, imageNumber: String) {
        val db = Firebase.database.reference
        val diaryRef = db.child("publicDiary")

        val snapshot = diaryRef.get().await()
        val joayoList = mutableListOf<Pair<String, Boolean>>()

        snapshot.children.forEach { userSnapshot ->
            userSnapshot.children.forEach { imagesSnapshot ->
                val diary = imagesSnapshot.child("images").getValue(PublicDiary::class.java)
                if (diary?.image == imageNumber) {
                    val joayo = imagesSnapshot.ref.child("joayo")
                    val joayoCount = joayo.get().await().childrenCount.toString()
                    val result = joayo.child(authNumber).get().await()
                    if (result.exists()) {
                        joayoList.add(Pair(joayoCount, result.getValue(Boolean::class.java) ?: false))
//                        positiveJoayoUiChange()
                        _joayoData.value = Pair(joayoCount.toInt(), result.getValue(Boolean::class.java) ?: false)

                    } else {
                        joayoList.add(Pair("0", result.getValue(Boolean::class.java) ?: false))
//                        negativeJoayoUiChange()
                        _joayoData.value = Pair(joayoCount.toInt(), result.getValue(Boolean::class.java) ?: false)

                    }
                }
            }
        }

        Log.d("sdfsdfsfds" , "${joayoList}")
    }
    // 좋아요 체크

    // 좋아요 누르면 UI 변경 ( 하트 색깔 회색<->빨강 , 좋아요 숫자 업데이트 )

    private var _joayoData = MutableStateFlow<Pair<Int, Boolean>?>(null)
    var joayoData : MutableStateFlow<Pair<Int, Boolean>?> = _joayoData

    // 호출 위치 :
    // 함수 기능 :
    fun positiveJoayoUiChange() {
        _joayoData.value = _joayoData.value?.let { (count , joayo) ->
            Pair(count + 1, !joayo)
        }

    }

    fun negativeJoayoUiChange() {
        _joayoData.value = _joayoData.value?.let { (count , joayo) ->
            Pair(count - 1, !joayo)
        }
    }


    // 좋아요 누르면 UI 변경 ( 하트 색깔 회색<->빨강 , 좋아요 숫자 업데이트 )

    // 특정 날짜 업데이트
    init {
        viewModelScope.launch {
            callPublicDiaryWorkerFunction()

            callDiaryWorkerFunction(_sort.value)
        }
    }

    // 테스트

//    private val _selectedDiary = MutableStateFlow<PublicDiary?>(null)
//    val selectedDiary : MutableStateFlow<PublicDiary?> = _selectedDiary
//    fun updateSelectedDiary(diary: PublicDiary) {
//        _selectedDiary.value = diary
//    }
//
//    // 좋아요 UI 변경 함수
//    fun positiveJoayoUiChangessssss() {
//        _selectedDiary.value?.let { diary ->
//            _selectedDiary.value = diary.copy(love = diary.love + 1)
//        }
//    }
//
//    fun negativeJoayoUiChangessssss() {
//        _selectedDiary.value?.let { diary ->
//            _selectedDiary.value = diary.copy(love = diary.love - 1)
//        }
//    }
}
