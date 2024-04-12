package com.company.data.repositoryimpl

import com.company.data.datasource.TempDataSource
import com.company.data.model.toDomainModel
import com.company.domain.model.TempModel
import com.company.domain.repository.TempRepository
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val dataSource: TempDataSource
): TempRepository {
    override fun getTempModel(): TempModel {
        return dataSource.getTempModel()
    }

}