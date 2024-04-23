package com.company.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.company.data.repositoryimpl.CoroutineWorkerRepositoryImpl
import com.company.data.repositoryimpl.GetBaseProductRespositoryImpl
import com.company.domain.usecase.GetBaseProductUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope

@HiltWorker
class GetBaseProductCoroutineWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val GetBaseProductRespositoryImpl: GetBaseProductRespositoryImpl,
    private val CoroutineWorkerRepositoryImpl : CoroutineWorkerRepositoryImpl
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope{
        try {
            GetBaseProductRespositoryImpl.getBaseProductList()
            Log.d("karina", "karinaT")
        } catch (e: Exception) {
            Result.failure()
            Log.d("karina", "karinaF")

        }
        Result.success()
    }

}

fun getInfoNowWork(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    // 빨리 해야되서 지연시간 X
    val immediateWorkRequest = OneTimeWorkRequestBuilder<GetBaseProductCoroutineWorker>()
        .setConstraints(constraints)
        .build()

    Log.d("first" , "dd")
    WorkManager.getInstance(context).enqueue(immediateWorkRequest)

}
