package com.company.designsystem.designsystem.component.card

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Locale


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
            Row (
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    "${day} ${diaryNumber} ${writer}",
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "커뮤니티에 자랑하기",
                    fontSize = 15.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "",
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

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

//            Row {
//                var like by remember { mutableStateOf(false) }
//                Icon(
//                    imageVector = if (like) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
//                    contentDescription = "Favorite",
//                    tint = if (like) Color.Red else Color.Gray,
//                    modifier = Modifier.clickable {
//                        like = !like
//                    }
//                )
//            }
        }
    }
}

fun fromFireStoreToPublicDiary(
    imageUri: Uri
) {
    val storageRef = FirebaseStorage.getInstance().getReference("images/${imageUri.lastPathSegment}")


//    saveImageUrlToRealtimeDatabase(imageUri.toString() , authNumber , diaryText , diaryNumber , authNickName)
}

fun saveImageUrlToRealtimeDatabase(imageUrl: String, authNumber: String , diaryText : String , diaryNumber : String , authNickName : String) {
    val databaseRef = Firebase.database.reference


    // 퍼블릭 다이어리에 내 다이어리 저장
    val publicDiaryRef = databaseRef.child("publicDiary/$authNumber")
    // 새 데이터를 추가하며 고유 ID 생성
    val newEntryRef = publicDiaryRef.push()
    // 고유 ID 아래에 images와 joayo 노드 생성


    ////

    val diaryDate = getCurrentDateString()
    val love : String = "0"
    val diary = mapOf(
        "image" to imageUrl,
        "diary" to diaryText,
        "day" to diaryDate,
        "diaryNumber" to diaryNumber
    )

    val publicDiaryImage = mapOf(
        "image" to imageUrl,
        "diary" to diaryText,
        "day" to diaryDate,
        "love" to love,
        "writer" to authNickName,
        "authNumber" to authNumber,
        "diaryNumber" to diaryNumber
    )
    newEntryRef.child("images").setValue(publicDiaryImage)
    newEntryRef.child("joayo").setValue("")

}

fun getCurrentDateString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(System.currentTimeMillis())
}

