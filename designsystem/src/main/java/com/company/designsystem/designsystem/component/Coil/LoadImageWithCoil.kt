package com.company.designsystem.designsystem.component.Coil

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.company.designsystem.R


@Composable
fun LoadImageWithCoil(imageUrl: String, context: Context) {
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )

}
