package com.company.data.repositoryimpl.publicdiary

import com.company.data.datasource.publicdiary.PublicDiaryDao
import com.company.data.mapper.publicdiary.toPublicDiary
import com.company.domain.entity.PublicDiary
import com.company.domain.repository.publicidary.GetPublicDiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPublicDiaryRepositoryImpl @Inject constructor(
    private val PublicDiaryDao : PublicDiaryDao
) : GetPublicDiaryRepository{
    override suspend fun getPublicDiary() : Flow<List<PublicDiary>> {
        return PublicDiaryDao.getPublicDiary().map {
            it.map {
                it.toPublicDiary()
            }
        }
    }

}