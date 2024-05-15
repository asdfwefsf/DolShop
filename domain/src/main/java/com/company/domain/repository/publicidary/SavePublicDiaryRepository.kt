package com.company.domain.repository.publicidary

import com.company.domain.entity.PublicDiary

interface SavePublicDiaryRepository {
    suspend fun savePublicDiary(publicDiary: PublicDiary)

}