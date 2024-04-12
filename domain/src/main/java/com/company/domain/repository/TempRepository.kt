package com.company.domain.repository

import com.company.domain.model.TempModel

interface TempRepository {
    fun getTempModel() : TempModel
}