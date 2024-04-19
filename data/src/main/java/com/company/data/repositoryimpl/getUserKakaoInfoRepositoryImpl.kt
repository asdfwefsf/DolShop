package com.company.data.repositoryimpl

import android.util.Log
import com.company.domain.repository.getUserKakaoInfoRepository
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

class getUserKakaoInfoRepositoryImpl @Inject constructor(

) : getUserKakaoInfoRepository {

    override suspend fun getUserKakaoInfo(list: MutableStateFlow<List<String>>) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("auth", "사용자 정보 요청 실패", error)

            } else if (user != null) {
                val userInfo = listOf(
                    "회원번호: ${user.id}",
                    "이메일: ${user.kakaoAccount?.email}",
                    "닉네임: ${user.kakaoAccount?.profile?.nickname}",
                    "프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
                list.value = userInfo // MutableStateFlow의 value를 갱신합니다.
                Log.i("auth", "사용자 정보 요청 성공\n${userInfo.joinToString(separator = "\n")}")
            }
        }
    }


}
