package com.company.dolshop.screens.screentype.communityscreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.company.designsystem.designsystem.component.card.DetailDialog
import com.company.designsystem.designsystem.component.card.SomenailCard
import com.company.dolshop.viewmodel.DolsViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.domain.entity.Diary
import com.company.domain.entity.PublicDiary
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(innerPadding: PaddingValues) {
    val dolsViewModel: DolsViewModel = hiltViewModel()
    val pullRefreshState = rememberPullToRefreshState()
//    val userViewModel : KakaoAuthiViewModel = hiltViewModel()
//    val me = userViewModel.userInfoList.collectAsState().value.authNumber
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
            PublicDiarys(innerPadding, dolsViewModel)
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }

}

@Composable
fun PublicDiarys(
    innerPadding: PaddingValues,
    viewModel: DolsViewModel,
) {
    val diaries: LazyPagingItems<PublicDiary> = viewModel.publicDiaryda.collectAsLazyPagingItems()
    val context: Context = LocalContext.current
    var selectedDiary by remember { mutableStateOf<PublicDiary?>(null) }
    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }
    val joayoData = remember { mutableStateOf<Pair<Int, Boolean>?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = innerPadding,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(diaries.itemCount) { index ->
            diaries[index]?.let { diary ->
                SomenailCard(
                    image = diary.image,
                    context = context,
                    onClick = { selectedDiary = diary }
                )
            }
        }
    }

    selectedDiary?.let { diary ->
        LaunchedEffect(true) {
            val result = viewModel.checkJoyao(diary.authNumber, diary.image)
            joayoData.value = Pair(result[0].first.toInt(), result[0].second)
            showDialog.value = true
//            selectedDiary = null
        }

        if (showDialog.value) {
            joayoData.value?.let { (joayoNumber, joayoBoolean) ->
                DetailDialog(
                    diary = diary,
                    onDismissRequest = {
                        showDialog.value = false
                        selectedDiary = null
                    },
                    joayo = { viewModel.toggleLike(diary.image, diary.authNumber, diary.love) },
                    joayoNumber = joayoNumber,
                    joayoBoolean = joayoBoolean
                )
            }
        }
    }
}

