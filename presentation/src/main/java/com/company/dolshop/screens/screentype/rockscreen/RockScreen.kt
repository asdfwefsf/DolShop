package com.company.dolshop.screens.screentype.rockscreen

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Bitmap
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.company.designsystem.designsystem.component.card.DetailCardsss
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
    val dolsViewModel : DolsViewModel = hiltViewModel()
    val pullRefreshState = rememberPullToRefreshState()

    if(pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            delay(1500)
            dolsViewModel.callDiaryWorkerFunction()
            Log.d("refresh" , "refresh")
            pullRefreshState.endRefresh()
        }
    }

    val userInfolist = viewmodel.userInfoList.collectAsState()
    val authNickName = userInfolist.value.authNicName
    //
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
            if(dolsViewModel.sort.collectAsState().value == "특정날") {
                callDatePickerDialog(dolsViewModel)
            }
            DaySortSelector(dolsViewModel)
            firstUI(authNickName, navController)
            ImageTest(innerPadding , dolsViewModel)
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }

    //


}

@Composable
fun DaySortSelector(viewModel: DolsViewModel) {
    val sortOptions = listOf("모두", "오늘", "특정날")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(sortOptions[0]) }

    Row {
        sortOptions.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        onOptionSelected(option)
                        viewModel.updateSort(option)
                    })
            ) {
                RadioButton(
                    selected = option == selectedOption,
                    onClick = {
                        onOptionSelected(option)
                        viewModel.updateSort(option)
                    }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

//Preview용 firstUI Composable Name
//@Composable
//fun firstUI(myName : String) {
@Composable
fun firstUI(myName: String, navController: NavController) {
    Row(modifier = Modifier.padding(Paddings.small)) {
        Text("${myName}님의 하루 일기", fontSize = 15.sp, color = Color.Black)
        Spacer(modifier = Modifier.fillMaxWidth(0.9f))
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "일기장 추가",
            modifier = Modifier
                .size(13.dp)
                .clickable {
                    navController.navigate(ScreenList.AddRockScreen.route)
                }
        )

    }
}

@Composable
fun ImageTest(innerPadding: PaddingValues , viewModel : DolsViewModel) {
//    val viewModel: DolsViewModel = hiltViewModel()
    val diaries: LazyPagingItems<Diary> = viewModel.diaryda.collectAsLazyPagingItems()


    val bitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
    ) {
        items(diaries) { diaries ->
            diaries?.let {
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    elevation = CardDefaults.cardElevation(4.dp)
//                ) {
//                    Column(modifier = Modifier.padding(8.dp)) {
//                        Text("${diaries.day} ${diaries.diaryNumber}", fontSize = 15.sp, color = Color.Black)

                        DetailCardsss(
                            day= diaries.day,
                            diaryNumber = diaries.diaryNumber,
                            writer = diaries.writer,
                            image = diaries.image,
                            diary = diaries.diary,
                        )


//                        Glide.with(LocalContext.current)
//                            .asBitmap()
//                            .load(diaries.image)
//                            .into(object : CustomTarget<Bitmap>() {
//                                override fun onResourceReady(
//                                    resource: Bitmap,
//                                    transition: Transition<in Bitmap>?
//                                ) {
//                                    bitmap.value = resource
//                                }
//                                override fun onLoadCleared(placeholder: Drawable?) {
//
//                                }
//                            })
//
//                        bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
//                            Image(
//                                bitmap = fetchedBitmap,
//                                contentDescription = "Diary Image",
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                            )
//                        } ?: Image(
//                            painter = painterResource(id = R.drawable.ic_launcher_background),
//                            contentDescription = "Default Image",
//                            contentScale = ContentScale.Fit,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(200.dp)
//                        )
//
//                        Text(
//                            text = diaries.diary,
//                            color = Color.Black,
//                            modifier = Modifier.padding(top = 8.dp)
//                        )
                    }
                }
            }
        }

//    }
//}

@Composable
fun callDatePickerDialog(viewModel: DolsViewModel, context: Context = LocalContext.current) {
    val showDialog = remember { mutableStateOf(false) }

    Button(onClick = { showDialog.value = true }) {
        Text("날짜 선택")
    }

    val scope = rememberCoroutineScope()
    if (showDialog.value) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val formattedMonth = String.format(Locale.KOREAN ,"%02d", month + 1)
                val formattedDay = String.format(Locale.KOREAN , "%02d", dayOfMonth)

                val selectedDate = "$year-$formattedMonth-$formattedDay"

                viewModel.updateSpecificDate(selectedDate)
                Log.d("Thibal" , selectedDate)

                showDialog.value = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}


//
//@Composable
//fun realCallCalander(context = LocalContext.current , viewModel: DolsViewModel) {
//    val calendar = Calendar.getInstance()
//    DatePickerDialog(
//        context,
//        { _, year, month, dayOfMonth ->
//            val selectedDate = "$year-${month + 1}-${dayOfMonth}"
//            viewModel.updateSpecificDate(selectedDate)
//            showDialog.value = false
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    ).show()
//}