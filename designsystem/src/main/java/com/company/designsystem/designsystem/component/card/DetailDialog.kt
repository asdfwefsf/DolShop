package com.company.designsystem.designsystem.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.domain.entity.PublicDiary

// DetailDialog는 PublicDiarays에서 특정한 퍼블릭다이어리 하나를 클릭하면 해당하는 단 하나의 퍼블릭 다이어리에
// 대한 정보를 보여주는 화면이다.
@Composable
fun DetailDialog(
    diary: PublicDiary,
    onDismissRequest: () -> Unit,
    joayoSet : () -> Unit,
    savePublicDiary : () -> Unit,
    joayoNumber: Int,
    joayoBoolean: Boolean
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            DetailCard(
                day = diary.day,
                diaryNumber = diary.diaryNumber,
                writer = diary.writer,
                image = diary.image,
                diary = diary.diary,
                loveNumber = diary.love,
                joayoNumber = joayoNumber,
                joayoBoolean = joayoBoolean,
                savePublicDiary = {
                    savePublicDiary()
                },
                joayoGet = {
                    joayoSet()
                },
            )
        }
    }
}

// DetailCar는 DeatilDialog에서만 보여진다.
@Composable
fun DetailCard(
    day: String,
    diaryNumber: String,
    writer: String,
    image: String,
    diary: String,
    loveNumber: String,
    joayoNumber: Int,
    joayoBoolean: Boolean,
    savePublicDiary : () -> Unit,
    joayoGet : () -> Unit,
) {

    var savePublicDiaryBoolean by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight(0.8f),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
        ) {
            items(1) {
                Text(
                    "${day} ${diaryNumber} ${writer}",
                    fontSize = 15.sp,
                    color = Color.Black
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .height(384.dp)
                ) {
                    LoadImageWithCoil(image, LocalContext.current)
                }
                Text(
                    text = diary,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row {
//                    var like by remember { mutableStateOf(false) }
                    Icon(
                        imageVector = if (joayoBoolean) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (joayoBoolean) Color.Red else Color.Gray,
                        modifier = Modifier.clickable {
//                            like = !like
                            joayoGet()
                        }
                    )
                    Spacer(Modifier.size(8.dp))
                    Text(joayoNumber.toString())
                    Icon(
                        imageVector = if (savePublicDiaryBoolean) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (savePublicDiaryBoolean) Color.Red else Color.Gray,
                        modifier = Modifier.clickable {
                            savePublicDiaryBoolean = !savePublicDiaryBoolean
                            savePublicDiary()
                        }
                    )

                }
            }
        }
    }
}
