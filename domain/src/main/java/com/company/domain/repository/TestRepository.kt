package com.company.domain.repository

import com.company.domain.entity.TestModel

interface TestRepository {
    fun getTestData() : TestModel?
}