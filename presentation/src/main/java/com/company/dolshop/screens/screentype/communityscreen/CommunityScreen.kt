package com.company.dolshop.screens.screentype.communityscreen

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.company.dolshop.viewmodel.DolsViewModel
import com.company.domain.entity.Diary
import com.company.presentation.R

@Composable
fun CommunityScreen(innerPadding: PaddingValues) {
    val dolsViewModel : DolsViewModel = hiltViewModel()
    Text("CommunityScreen")
    publicDiaryLogicTestUI(innerPadding , dolsViewModel)
}

@Composable
fun publicDiaryLogicTestUI(innerPadding: PaddingValues, viewModel : DolsViewModel) {
//    val viewModel: DolsViewModel = hiltViewModel()
    val diaries: LazyPagingItems<Diary> = viewModel.publicDiaryda.collectAsLazyPagingItems()


    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
    ) {
        items(diaries) { diaries ->
            diaries?.let {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("${diaries.day} ${diaries.diaryNumber}", fontSize = 15.sp, color = Color.Black)
                        Glide.with(LocalContext.current)
                            .asBitmap()
                            .load(diaries.image)
                            .into(object : CustomTarget<Bitmap>() {
                                override fun onResourceReady(
                                    resource: Bitmap,
                                    transition: Transition<in Bitmap>?
                                ) {
                                    bitmap.value = resource
                                }
                                override fun onLoadCleared(placeholder: Drawable?) {

                                }
                            })

                        bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
                            Image(
                                bitmap = fetchedBitmap,
                                contentDescription = "Diary Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        } ?: Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Default Image",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Text(
                            text = diaries.diary,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }

    }
}