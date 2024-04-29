package com.company.domain.usecase.worker

import android.content.Context
import com.company.domain.repository.CoroutineWorkerRepository
import javax.inject.Inject

class CoroutineWorkerUseCase @Inject constructor(
    private val repository : CoroutineWorkerRepository
) {
    operator fun invoke(context : Context) {
        repository.callGetInfoNowWork(context)
    }
}