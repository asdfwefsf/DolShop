package com.company.domain.repository.publicidary

import com.company.domain.entity.PublicDiary

interface DeletePublicDiaryRepository {
    suspend fun deletePublicDiary(publicDiary: PublicDiary)
}