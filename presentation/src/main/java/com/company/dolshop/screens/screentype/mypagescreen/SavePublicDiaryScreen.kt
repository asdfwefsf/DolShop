package com.company.dolshop.screens.screentype.mypagescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.company.designsystem.designsystem.component.card.fromFireStoreToPublicDiary
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.viewmodel.publicdiary.PublicDiaryViewModel
import com.company.domain.entity.PublicDiary
import com.company.presentation.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SavePublicDiaryScreen(
    navController: NavController
) {

    val publicDiaryviewModel: PublicDiaryViewModel = hiltViewModel()
    // collectAsStateWithLifecycle() : Flow를 가져와서 State로 변환 -> 생명주기OK -> 리컴포지션 발생 시킴
    val publicDiaryList by publicDiaryviewModel.publicDiary.collectAsStateWithLifecycle()


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.navigate(ScreenList.MyPageScreen.route) {
                        popUpTo(ScreenList.MyPageScreen.route) {
                            inclusive = true
                        }
                    }
                }

        )
        Spacer(modifier = Modifier.weight(1f))
        Text("저장일기", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.weight(1.1f))
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 30.dp).background(Color.White)
    ) {
        items(publicDiaryList.size) { index ->
            PublicDiaryItem(publicDiaryviewModel, index, publicDiaryList)
        }
    }
}


// TODO 아래는 테스트 유아이임. -> 추후 수정 예정
@Composable
fun PublicDiaryItem(
    viewmodel: PublicDiaryViewModel,
    index: Int,
    publicDiaryList: List<PublicDiary>
) {
    val scope = rememberCoroutineScope()
    val publicDiary = publicDiaryList[index]
    var loadState by remember { mutableStateOf("") }

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(publicDiary.image)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    publicDiary.day,
//                    "${day} ${diaryNumber}",
                    fontSize = 15.sp,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    publicDiary.writer,
//                    "${day} ${diaryNumber}",
                    fontSize = 15.sp,
                    color = Color.Black,
                )
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "",
                    tint = Color(0xFF7BF579),
                    modifier = Modifier.clickable {
                        scope.launch(Dispatchers.IO) {
                            viewmodel.deletePublicDiary(publicDiary)
                        }
                    }
                )
            }
            AsyncImage(
                model = imageRequest,
                contentDescription = "",
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            )
            Text(
                text = publicDiary.diary,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

