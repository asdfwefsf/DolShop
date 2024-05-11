package com.company.designsystem.designsystem.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun RockScreenCard(
    day: String,
    diaryNumber: String,
    writer: String,
    image: String,
    diary: String
) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                "${day} ${diaryNumber} ${writer}",
                fontSize = 15.sp,
                color = Color.Black
            )
            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
            )
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
                    }
                )
            }
        }
    }
}
