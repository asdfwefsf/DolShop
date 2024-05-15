package com.company.data.repositoryimpl.publicdiary

import com.company.data.datasource.publicdiary.PublicDiaryDao
import com.company.data.mapper.publicdiary.toPublicDiaryInfo
import com.company.domain.entity.PublicDiary
import com.company.domain.repository.publicidary.DeletePublicDiaryRepository
import javax.inject.Inject

class DeletePublicDiaryRepositoryImpl @Inject constructor(
    private val dao : PublicDiaryDao
) : DeletePublicDiaryRepository {
    override suspend fun deletePublicDiary(publicDiary: PublicDiary) {
        val mapped = publicDiary.toPublicDiaryInfo()
        dao.deletePublicDiaryInfo(mapped)
    }
}