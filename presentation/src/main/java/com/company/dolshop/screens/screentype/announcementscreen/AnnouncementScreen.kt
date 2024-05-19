package com.company.dolshop.screens.screentype.announcementscreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.dolshop.screens.screentype.productscreen.applyCubic
import com.company.dolshop.viewmodel.AnnouncementViewModel
import com.company.dolshop.viewmodel.UpdateBaseProductViewModel
import com.company.domain.entity.Advertisement
import com.company.presentation.R

@Composable
fun AnnouncementScreen() {
    val announcementViewModel: AnnouncementViewModel = hiltViewModel()
    Column {
        Text("AnnouncementScreen")
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
            .data(viewmodel.announcement.collectAsState().value[0].AdvertisementImage)
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
                text = viewmodel.announcement.collectAsState().value[0].AdvertisementMunGu,
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


    }
}
