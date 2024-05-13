package com.company.designsystem.designsystem.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.domain.entity.Diary
import com.company.domain.entity.PublicDiary

@Composable
fun DetailDialog(diary: PublicDiary, onDismissRequest: () -> Unit , joayo : () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest,
        ) {
        DetailCard(
            day = diary.day,
            diaryNumber = diary.diaryNumber,
            writer = diary.writer,
            image = diary.image,
            diary = diary.diary,
            loveNumber = diary.love,
            onClick = { joayo() }
        )
    }
}

@Composable
fun DetailCard(
    day: String,
    diaryNumber: String,
    writer: String,
    image: String,
    diary: String,
    loveNumber : String,
    onClick : () -> Unit
) {


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
                LoadImageWithCoil(image , LocalContext.current)
                Text(
                    text = diary,
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
                            onClick()
                        }
                    )
                    Spacer(Modifier.size(8.dp))

                    Text(loveNumber)
                }
            }
        }
    }
}
