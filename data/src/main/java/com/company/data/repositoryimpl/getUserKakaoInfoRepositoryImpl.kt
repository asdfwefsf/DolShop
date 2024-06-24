package com.company.data.repositoryimpl

import android.util.Log
import com.company.data.datasource.local.baseproductinfo1.BaseProductInfo
import com.company.data.datasource.local.userinfo.UserInfo
import com.company.data.datasource.local.userinfo.UserInfoDao
import com.company.domain.repository.getUserKakaoInfoRepository
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetUserKakaoInfoRepositoryImpl @Inject constructor(
    private val dao: UserInfoDao
) : getUserKakaoInfoRepository {

    val scope = CoroutineScope(Dispatchers.IO)

    override suspend fun getUserKakaoInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("auth", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                val userInfo = UserInfo(
                    1,
                    "${user.id}",
                    "${user.kakaoAccount?.email}",
                    "${user.kakaoAccount?.profile?.nickname}",
                    "${user.kakaoAccount?.profile?.thumbnailImageUrl}",
                    " "
                )
                scope.launch {
                    if (!dao.prodideUserInfoExists(1)) {
                        dao.insertUserInfo(
                            UserInfo(
                                userInfo.id,
                                userInfo.authNumber,
                                userInfo.authEmail,
                                userInfo.authNickName,
                                userInfo.authProfileImage,
                                " "
                            )
                        )
                        Log.d("daoTest", "userinfo insert")
                    } else {
                        dao.updateUserInfo(
                            UserInfo(
                                userInfo.id,
                                userInfo.authNumber,
                                userInfo.authEmail,
                                userInfo.authNickName,
                                userInfo.authProfileImage,
                                " "
                            )
                        )
                        Log.d("daoTest", "userinfo update")
                    }
                }
            }
        }
    }
}
