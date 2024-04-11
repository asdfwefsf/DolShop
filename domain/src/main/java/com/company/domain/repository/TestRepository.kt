package com.company.domain.repository

import com.company.domain.model.TestModel

interface TestRepository {
    fun getTestData() : TestModel?
}