package com.company.data.repositoryimpl

import android.content.Context
import com.company.data.worker.getInfoNowWork
import com.company.domain.repository.CoroutineWorkerRepository
import javax.inject.Inject

class CoroutineWorkerRepositoryImpl @Inject constructor(

) : CoroutineWorkerRepository {
    override fun callGetInfoNowWork(context : Context) {
        getInfoNowWork(context)
    }
}