package com.company.data.repositoryimpl

import android.content.Context
import android.util.Log
import com.company.domain.repository.KakaoLoginRepository
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class KakaoLoginRepositoryImpl @Inject constructor(
    private val context : Context
) : KakaoLoginRepository {
    override suspend fun kakaoLogin(): Boolean = suspendCancellableCoroutine {returnvalue ->
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("login", "카카오계정으로 로그인 실패", error)
                returnvalue.resume(false)

            } else if (token != null) {
                Log.i("login", "카카오계정으로 로그인 성공 ${token.accessToken}")
                Log.i("login", "카카오계정으로 로그인 성공 Tldl씨이발")
                returnvalue.resume(true)

            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e("login", "카카오톡으로 로그인 실패", error)

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {

                        return@loginWithKakaoTalk
                    }

                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i("login", "카카오톡으로 로그인 성공 ${token.accessToken}")

                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }
}