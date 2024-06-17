package com.company.data.repositoryimpl.publicdiary

import com.company.data.datasource.local.publicdiary.PublicDiaryDao
import com.company.data.mapper.publicdiary.toPublicDiaryInfo
import com.company.domain.entity.PublicDiary
import com.company.domain.repository.publicidary.SavePublicDiaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavePublicDiaryRepositoryImpl @Inject constructor(
    private val publicDiaryDao: PublicDiaryDao
) : SavePublicDiaryRepository {

    override suspend fun savePublicDiary(publicDiary: PublicDiary) {
        val mapperSucced = publicDiary.toPublicDiaryInfo()
        withContext(Dispatchers.IO) {
            publicDiaryDao.insertPublicDiaryInfo(mapperSucced)

        }
    }
}