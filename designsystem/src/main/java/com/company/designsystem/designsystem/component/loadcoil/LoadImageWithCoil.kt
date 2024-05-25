package com.company.designsystem.designsystem.component.loadcoil

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest


@Composable
fun LoadImageWithCoil(
    imageUrl: String,
    context: Context,
) {
    val localDensity = LocalDensity.current
    var heightDp by remember { mutableStateOf(0.dp) }

    var loadState by remember { mutableStateOf("loading") }

    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                heightDp = with(localDensity) { coordinates.size.height.toDp() }
            }
        ,
        contentScale = ContentScale.Crop,
        onLoading = { loadState = "loading" },
        onError = { loadState = "error" },
        onSuccess = { loadState = "success" }
    )
    Log.d("height" , heightDp.toString())

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

        "success" -> {
        }

    }
}


@Preview
@Composable
fun LoadImageWithCoilPreview() {
    LoadImageWithCoil(
        "https://m.segye.com/content/image/2021/11/16/20211116509557.jpg",
        LocalContext.current
    )
}
