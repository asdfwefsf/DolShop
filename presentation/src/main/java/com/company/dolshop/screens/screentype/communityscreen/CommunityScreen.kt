package com.company.dolshop.screens.screentype.communityscreen

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.dolshop.viewmodel.publicdiary.PublicDiaryViewModel
import com.company.domain.entity.PublicDiary

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

    val publicDiaryViewModel : PublicDiaryViewModel = hiltViewModel()
    val kakaoAuthiViewModel : KakaoAuthiViewModel = hiltViewModel()
    val myAuthNumber = kakaoAuthiViewModel.userInfoList.collectAsState().value.authNumber

//    var joayoData = remember { mutableStateOf<Pair<Int, Boolean>?>(null) }
    val showDialog = remember { mutableStateOf(false) }

    // gonee1o
    val joayoData by viewModel.joayoData.collectAsState()

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


    // 다이어리들에서 다이어리 클릭하면 클릭된 다이어리가 selectedDiary
    selectedDiary?.let { diary ->
        LaunchedEffect(true) {
            // 현재 해당 퍼블릭다이어리의 좋아요 관련 정보 가져오기
            // 여기의 authNumber는 사용자 authNumber가 들어가야 된다.
            viewModel.getJoyaoFromFirebase(myAuthNumber, diary.image)
            // 다이어리들에서 클릭된 다이어리 가져온게 diary , null이 아니라서 let에서 diary-> 로 받아온거야


            // gonee
//            joayoData.value = Pair(result[0].first.toInt(), result[0].second)
            //
            showDialog.value = true
        }

        if (showDialog.value) {
            // gonee2
            joayoData?.let { (joayoNumber, joayoBoolean) ->
                // gonee3
//                (joayoNumber, joayoBoolean) ->
                DetailDialog(
                    // DetailDialog에 들어갈 퍼블릭 다이어리 객체가 diary인데 이게 들어가는거야
                    diary = diary,
                    onDismissRequest = {
                        showDialog.value = false
                        // 퍼블릭다이어리들중에서 다른 다이어리 들어갈 수 있게 selectedDiary 값 초기화
                        selectedDiary = null
                    },
                    joayoSet = {
                        //
                        viewModel.setJoyaoToFirebase(diary.image, myAuthNumber , diary)
                    },
//                    joayoUpdate = {
//                        if(joayoBoolean) {
//                            viewModel.negativeJoayoUiChange()
//                            Log.d("joayo" , "positive : ${joayoBoolean.toString()}")
//                            Log.d("joayo" , "positive : ${diary.love}")
//                        } else {
//                            viewModel.positiveJoayoUiChange()
//                            Log.d("joayo" , "negative : ${joayoBoolean.toString()}")
//                            Log.d("joayo" , "negative : ${diary.love}")
//                        }
//                    },
                    // gonee4
                    savePublicDiary = {
                        publicDiaryViewModel.savePublicDiary(diary)
                    },
                    joayoNumber = joayoData?.first ?: 0,
                    joayoBoolean = joayoData?.second ?: false
                )
            }
        }
    }
}


