package com.company.dolshop.screens.screentype.communityscreen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.company.designsystem.designsystem.component.card.DetailDialog
import com.company.designsystem.designsystem.component.card.SomenailCard
import com.company.dolshop.viewmodel.DolsViewModel
import com.company.domain.entity.Diary

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
    val diaries: LazyPagingItems<Diary> = viewModel.publicDiaryda.collectAsLazyPagingItems()
    val context: Context = LocalContext.current
    var selectedDiary by remember { mutableStateOf<Diary?>(null) }

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

    selectedDiary?.let {
        DetailDialog(diary = it, onDismissRequest = { selectedDiary = null })
    }
}

