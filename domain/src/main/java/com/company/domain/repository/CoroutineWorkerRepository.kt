package com.company.domain.repository

import android.content.Context

interface CoroutineWorkerRepository {
    fun callGetInfoNowWork(context : Context)
}