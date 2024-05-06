package com.company.data.repositoryimpl

import com.company.data.datasource.diarynumber.DiaryNumberDao
import com.company.data.datasource.diarynumber.DiaryNumberInfo
import com.company.domain.repository.DiaryNumberRepository
import javax.inject.Inject

class DiaryNumberRepositoryImpl @Inject constructor(
    private val dao : DiaryNumberDao
) : DiaryNumberRepository {
    override suspend fun getDiaryNumber() : Int {
        return dao.getDiaryNumber()?.diaryNumber ?: 0
    }

    override suspend fun addOneDiaryNumber() {
        dao.addOneDiaryNumber()
    }

    override suspend fun insertDiaryNumber() {
        dao.insertDiaryNumber(DiaryNumberInfo(0 , 0))
    }


}