package com.company.data.repositoryimpl

import android.content.Context
import android.util.Log
import com.company.domain.repository.KakaoLogoutRepository
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class KakaoLogoutRepositoryImpl @Inject constructor(
    private val context : Context
) : KakaoLogoutRepository {
    override suspend fun kakaoLogout(): Boolean = suspendCancellableCoroutine {returnvalue ->
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e("logout", "로그아웃 실패 , SDK에서 토큰 삭제됨", error)
                returnvalue.resume(false)
            } else {
                Log.i("logout", "로그아웃 성공 , SDK에서 토큰 삭제됨")
                returnvalue.resume(true)

            }

        }
    }
}