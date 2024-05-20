package com.company.dolshop.screens.screentype.rockscreen

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.company.designsystem.designsystem.Paddings
import com.company.designsystem.designsystem.component.card.RockScreenCard
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.DolsViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.domain.entity.Diary
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocksScreen(
    innerPadding: PaddingValues,
    viewmodel: KakaoAuthiViewModel,
    navController: NavController,
) {
    val dolsViewModel: DolsViewModel = hiltViewModel()
    val pullRefreshState = rememberPullToRefreshState()

    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            delay(1500)
            dolsViewModel.callDiaryWorkerFunction(dolsViewModel.sort.value)
            Log.d("refresh", "refresh")
            pullRefreshState.endRefresh()
        }
    }
    LaunchedEffect(true) {
        dolsViewModel.callDiaryWorkerFunction(dolsViewModel.sort.value)
        Log.d("refresh", "refresh")
    }

    val userInfolist = viewmodel.userInfoList.collectAsState()
    val authNickName = userInfolist.value.authNicName
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
            DaySortSelector(dolsViewModel)
            FirstUI(authNickName, navController)
            RockImage(innerPadding, dolsViewModel)
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }
}

//@Composable
//fun DaySortSelector(viewModel: DolsViewModel) {
//    val sortOptions = listOf("모두", "오늘", "특정날")
//    val (selectedOption, onOptionSelected) = remember { mutableStateOf(sortOptions[0]) }
//
//    Row {
//        sortOptions.forEach { option ->
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .clickable(onClick = {
//                        onOptionSelected(option)
//                        viewModel.updateSort(option)
//                    })
//            ) {
//                RadioButton(
//                    selected = option == selectedOption,
//                    onClick = {
//                        onOptionSelected(option)
//                        viewModel.updateSort(option)
//                    }
//                )
//                Text(
//                    text = option,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
//            }
//        }
//    }
//}

@Composable
fun FirstUI(myName: String, navController: NavController) {
    Row(modifier = Modifier.padding(Paddings.small)) {
        Text("${myName}님의 하루 일기", fontSize = 15.sp, color = Color.Black)
        Spacer(modifier = Modifier.fillMaxWidth(0.9f))
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "일기장 추가",
            modifier = Modifier
                .size(15.dp)
                .clickable {
                    navController.navigate(ScreenList.AddRockScreen.route)
                }
        )

    }
}

@Composable
fun RockImage(innerPadding: PaddingValues, viewModel: DolsViewModel) {
    val diaries: LazyPagingItems<Diary> = viewModel.diaryda.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
    ) {
        items(diaries) { diaries ->
            diaries?.let {
                RockScreenCard(
                    day = diaries.day,
                    diaryNumber = diaries.diaryNumber,
                    writer = diaries.writer,
                    image = diaries.image,
                    diary = diaries.diary,
                )
            }
        }
    }
}


@Composable
fun DaySortSelector(viewModel: DolsViewModel) {
    val sortOptions = listOf("모두", "오늘", "특정날")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(sortOptions[0]) }
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showDialog.value) {
        CallDatePickerDialog(viewModel, context, showDialog)
    }
    Row {
        sortOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
//                    .padding(8.dp)
//                    .clickable(onClick = {
//                        onOptionSelected(option)
//                        viewModel.updateSort(option)
//                        if (option == "특정날") {
//                            showDialog.value = true
////                            CallDatePickerDialog(viewModel, context, showDialog)
//
//                        }
//                    })
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = {
                        onOptionSelected(option)
                        viewModel.updateSort(option)
                        if (option == "특정날") {
                            showDialog.value = true
//                            CallDatePickerDialog(viewModel, context, showDialog)

                        }
                    }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 1.dp)
                )
            }
        }
    }
}

@Composable
fun CallDatePickerDialog(
    viewModel: DolsViewModel,
    context: Context = LocalContext.current,
    showDialog: MutableState<Boolean>
) {
//    if (showDialog.value) {
    showDialog.value = false

    val calendar = Calendar.getInstance()
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val formattedMonth = String.format(Locale.KOREAN, "%02d", month + 1)
            val formattedDay = String.format(Locale.KOREAN, "%02d", dayOfMonth)

            val selectedDate = "$year-$formattedMonth-$formattedDay"

            viewModel.updateSpecificDate(selectedDate)
            Log.d("Thibal", selectedDate)

            showDialog.value = false
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
//    }
}

