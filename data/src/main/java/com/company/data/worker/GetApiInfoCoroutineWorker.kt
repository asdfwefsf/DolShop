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
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope

@HiltWorker
class GetApiInfoCoroutineWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getBaseProductWorkerFunction1: GetBaseProductWorkerFunction1,
    private val getProductSaleWorkerFunction : GetProductSaleWorkerFunction
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope{
        try {
            getBaseProductWorkerFunction1.getBaseProductList1()
            getProductSaleWorkerFunction.getProductSaleInfo()
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

    val immediateWorkRequest = OneTimeWorkRequestBuilder<GetApiInfoCoroutineWorker>()
        .setConstraints(constraints)
        .build()

    Log.d("first" , "dd")
    WorkManager.getInstance(context).enqueue(immediateWorkRequest)

}






// core 또는 common 모듈































