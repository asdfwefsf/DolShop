package com.company.data.repositoryimpl

import com.company.data.datasource.TestDataSource
import com.company.data.model.toDomainModel
import com.company.domain.model.TestModel
import com.company.domain.repository.TestRepository

class TestRepositoryImpl(val dataSource: TestDataSource) : TestRepository {
    override fun getTestData() : TestModel? {
        return dataSource.getTestModelResponse().toDomainModel()
    }
}