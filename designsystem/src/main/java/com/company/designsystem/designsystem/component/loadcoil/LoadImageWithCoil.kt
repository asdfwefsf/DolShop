package com.company.designsystem.designsystem.component.loadcoil

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest


@Composable
fun LoadImageWithCoil(
    imageUrl: String,
    context: Context,
) {

    var loadState by remember { mutableStateOf("loading") }  // 로드 상태를 관리할 상태 변수

    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
//        .placeholder(R.drawable.ic_launcher_background)
//        .error(R.drawable.ic_launcher_background)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.Crop,
        onLoading = { loadState = "loading" },
        onError = { loadState = "error" },
        onSuccess = { loadState = "success" }
    )

    when (loadState) {
        "loading" -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }

        "error" -> Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Icon(Icons.Filled.Close, contentDescription = "Error", modifier = Modifier.size(24.dp))
        }

        "success" -> { /* 이미지 로드 성공시 필요한 UI 처리 */
        }
    }
}
