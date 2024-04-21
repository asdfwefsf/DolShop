package com.company.data.repositoryimpl

import android.util.Log
import com.company.data.datasource.userinfo.UserInfo
import com.company.data.datasource.userinfo.UserInfoDao
import com.company.domain.repository.getUserKakaoInfoRepository
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class getUserKakaoInfoRepositoryImpl @Inject constructor(
    private val dao: UserInfoDao
) : getUserKakaoInfoRepository {

    val scope = CoroutineScope(Dispatchers.IO)


    override suspend fun getUserKakaoInfo() {
//        scope.launch {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("auth", "사용자 정보 요청 실패", error)

            } else if (user != null) {
                val userInfo = UserInfo(
                    0,
                    "${user.id}",
                    "${user.kakaoAccount?.email}",
                    "${user.kakaoAccount?.profile?.nickname}",
                    "${user.kakaoAccount?.profile?.thumbnailImageUrl}"

                )
                Log.i("auth", "사용자 정보 요청 성공\n${userInfo.id}")
                Log.i("auth", "사용자 정보 요청 성공\n${userInfo.authNumber}")
                Log.i("auth", "사용자 정보 요청 성공\n${userInfo.authEmail}")
                Log.i("auth", "사용자 정보 요청 성공\n${userInfo.authNicName}")
                Log.i("auth", "사용자 정보 요청 성공\n${userInfo.authProfileImage}")

                scope.launch {
                    dao.upsertUserInfo(
                        UserInfo(
                            userInfo.id,
                            userInfo.authNumber,
                            userInfo.authEmail,
                            userInfo.authNicName,
                            userInfo.authProfileImage,
                        )
                    )
                }


            }
        }

    }


}
