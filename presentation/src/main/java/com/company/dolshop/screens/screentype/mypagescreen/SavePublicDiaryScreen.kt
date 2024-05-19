package com.company.dolshop.screens.screentype.mypagescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.company.dolshop.viewmodel.publicdiary.PublicDiaryViewModel
import com.company.domain.entity.PublicDiary
import com.company.presentation.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SavePublicDiaryScreen(
) {

    val publicDiaryviewModel: PublicDiaryViewModel = hiltViewModel()

    // collectAsStateWithLifecycle() : Flow를 가져와서 State로 변환 -> 생명주기OK -> 리컴포지션 발생 시킴
    val publicDiaryList by publicDiaryviewModel.publicDiary.collectAsStateWithLifecycle()

    LazyColumn(
    ) {
        items(publicDiaryList.size) { index ->
//            publicDiaryList[index].let {
            PublicDiaryItem(publicDiaryviewModel, index, publicDiaryList)
//            }
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

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (baseImage, diaryImage, diary, writerText, dayText , loadingBox) = createRefs()

        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(publicDiary.image)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()

        // TODO 위치 조정


        Image(
            painter = painterResource(id = R.drawable.seve_publicdiary_base_image),
            contentDescription = "",
            modifier = Modifier.constrainAs(baseImage) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )

        AsyncImage(
            model = imageRequest,
            contentDescription = "",
            modifier = Modifier
                .height(200.dp)
                .constrainAs(diaryImage) {
                    top.linkTo(baseImage.top, margin = 4.dp)
                    start.linkTo(baseImage.start, margin = 4.dp)
                },
            onLoading = { loadState = "loading" },
            onSuccess = { loadState = "success"},
            onError = { loadState = "error" }

            )
        when (loadState) {

            "loading" -> Box(
                modifier = Modifier.fillMaxSize().constrainAs(loadingBox) {
                    centerTo(baseImage)
                },
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }

            "error" -> Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Icon(Icons.Filled.Close, contentDescription = "Error", modifier = Modifier.size(24.dp))
            }

            "success" -> {
            }

        }

        Text(
            text = publicDiary.writer,
            modifier = Modifier.constrainAs(writerText) {
                top.linkTo(baseImage.top, margin = 4.dp)
                start.linkTo(diaryImage.end, margin = 8.dp)
            },
            color = Color.White
        )

        Text(
            text = publicDiary.day,
            modifier = Modifier.constrainAs(dayText) {
                start.linkTo(writerText.end , margin = 4.dp)
                top.linkTo(baseImage.top , margin = 4.dp)
            },
            color = Color.White
        )

        LazyColumn(
            modifier = Modifier
                .height(300.dp)
                .constrainAs(diary) {
                    start.linkTo(diaryImage.end, margin = 8.dp)
                    top.linkTo(writerText.bottom , margin = 4.dp)
                }
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
//                    Row{
//                        Text(
//                            text = publicDiary.writer,
//                            color = Color.White
//                        )
//
//                        Text(
//                            text = publicDiary.day,
//                            color = Color.White
//
//                        )
//                    }
                    Text(
                        publicDiary.diary,
                        color = Color.White
                    )
                }
            }
        }
    }

    Icon(
        imageVector = Icons.Filled.Delete,
        contentDescription = "",
        modifier = Modifier.clickable {
            scope.launch(Dispatchers.IO) {
                viewmodel.deletePublicDiary(publicDiary)
            }
        }
    )

}

