package com.company.dolshop.screens.screentype.communityscreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.company.dolshop.viewmodel.DolsViewModel
import com.company.domain.entity.Diary
import com.company.presentation.R
import com.google.android.material.progressindicator.CircularProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(innerPadding: PaddingValues) {
    val dolsViewModel: DolsViewModel = hiltViewModel()
    val pullRefreshState = rememberPullToRefreshState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(pullRefreshState.nestedScrollConnection)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(pullRefreshState.nestedScrollConnection)
        ) {
            Text("CommunityScreen")
            PublicDiaryLogicTestUI(innerPadding, dolsViewModel)
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }

}

@Composable
fun PublicDiaryLogicTestUI(innerPadding: PaddingValues, viewModel: DolsViewModel) {
    val diaries: LazyPagingItems<Diary> = viewModel.publicDiaryda.collectAsLazyPagingItems()
    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }

    val context : Context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
    ) {
        items(diaries) { diaries ->
//            var like by remember { mutableStateOf(diaries.isLiked) } // isLiked는 각 Diary 객체의 초기 좋아요 상태를 나타냅니다.

            diaries?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(Color.White)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            "${diaries.day} ${diaries.diaryNumber} ${diaries.writer}",
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        LoadImageWithCoil(diaries.image, context)

                        Text(
                            text = diaries.diary,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Row {
                            var like by remember { mutableStateOf(false) }
                            Icon(
                                imageVector = if (like) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (like) Color.Red else Color.Gray,
                                modifier = Modifier.clickable {
                                    like = !like
                                }
                            )
                        }

                    }
                }
            }
        }

    }
}

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
        contentScale = ContentScale.Crop,

    )
}