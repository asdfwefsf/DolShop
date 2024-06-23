package com.company.dolshop.screens.screentype.announcementscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.company.dolshop.viewmodel.AnnouncementViewModel

@Composable
fun AnnouncementScreen() {
    val announcementViewModel: AnnouncementViewModel = hiltViewModel()
    Column {
        Text("준비중입니다.")
        AdvertisementTestScreen(announcementViewModel, {})
    }


}


@Composable
fun AdvertisementTestScreen(
//    productState: State<List<Advertisement>>,
    viewmodel : AnnouncementViewModel,
    onClick: (category: String) -> Unit
) {
    if (viewmodel.announcement.collectAsState().value.isNotEmpty()) {
//        val imageUrl = productState.value[0].AdvertisementImage
//        Log.d("sljfoej", imageUrl)
//        Log.d("sfec", imageUrl)

        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(viewmodel.announcement.collectAsState().value[0].announcementImage)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()



        Box {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = viewmodel.announcement.collectAsState().value[0].announcementName,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


    }
}
