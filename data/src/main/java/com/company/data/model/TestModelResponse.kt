package com.company.data.model

import com.company.domain.model.TempModel

class TestModelResponse(val name : String?)

// DataLayer에서 사용되는 모델을 DomainLayer에서 사용되는 모델로 변환하기
fun TestModelResponse.toDomainModel() : TempModel? {
    if (name != null) {
        return TempModel(name)
    }
    return null
}