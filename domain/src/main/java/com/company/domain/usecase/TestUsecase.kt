package com.company.domain.usecase

import com.company.domain.entity.TestModel
import com.company.domain.repository.TestRepository

class TestUsecase (
    val repository: TestRepository
) {

}