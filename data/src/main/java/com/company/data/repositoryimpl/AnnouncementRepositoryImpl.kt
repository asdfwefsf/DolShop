package com.company.data.repositoryimpl

import com.company.data.mapper.toDomainAnnouncementModel
import com.company.data.mapper.toDomainProductModel
import com.company.domain.model.DomainAnnouncementModel
import com.company.domain.model.DomainProductModel
import com.company.domain.repository.AnnouncementRepository
import com.company.network.api.AnnouncementAPI
import com.company.network.api.ProductAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AnnouncementRepositoryImpl @Inject constructor(
    private val announcementAPI: AnnouncementAPI
) : AnnouncementRepository{
    override fun getAnnouncement() = flow {
        val response = announcementAPI.getAnnouncement1()
        if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                emit(responseBody.map { it.toDomainAnnouncementModel() })
            }
        } else {
            emit(emptyList<DomainAnnouncementModel>())
        }
    }.flowOn(Dispatchers.IO)
}