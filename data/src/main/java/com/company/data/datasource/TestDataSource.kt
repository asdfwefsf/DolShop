package com.company.data.datasource

import com.company.data.model.TestModelResponse

// data source를 통해서 값을 가져올 때 TestModelResponse 필드가 반드시 채워지지 않을 수 있어.
class TestDataSource {
    fun getTestModelResponse() : TestModelResponse {
        return TestModelResponse("response")
    }
}