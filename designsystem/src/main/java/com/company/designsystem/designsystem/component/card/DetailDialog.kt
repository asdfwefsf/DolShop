package com.company.designsystem.designsystem.component.card

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.company.designsystem.R
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.domain.entity.PublicDiary

// DetailDialog는 PublicDiarays에서 특정한 퍼블릭다이어리 하나를 클릭하면 해당하는 단 하나의 퍼블릭 다이어리에
// 대한 정보를 보여주는 화면이다.
@Composable
fun DetailDialog(
    diary: PublicDiary,
    currentAppUserName : String,
    onDismissRequest: () -> Unit,
    joayoSet : () -> Unit,
    deletePublicDiary : () -> Unit,
    savePublicDiary : () -> Unit,
    joayoNumber: Int,
    joayoBoolean: Boolean,
    myAuthNumber : String,
    writerAuthNumber : String
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            DetailCard(
                day = diary.day,
                diaryNumber = diary.diaryNumber,
                writer = diary.writer,
                currentAppUserName = currentAppUserName,
                image = diary.image,
                diary = diary.diary,
                loveNumber = diary.love,
                joayoNumber = joayoNumber,
                joayoBoolean = joayoBoolean,
                myAuthNumber = myAuthNumber,
                writerAuthNumber = writerAuthNumber,
                deletePublicDiary = {
                    deletePublicDiary()
                },
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
    currentAppUserName : String,
    image: String,
    diary: String,
    loveNumber: String,
    joayoNumber: Int,
    joayoBoolean: Boolean,
    myAuthNumber : String,
    writerAuthNumber : String,
    deletePublicDiary : () -> Unit,
    savePublicDiary : () -> Unit,
    joayoGet : () -> Unit,
) {
    val context = LocalContext.current
    var savePublicDiaryBoolean by remember { mutableStateOf(false) }
    // TODO 여기서 authNumber가 같아야 삭제 할 수 있는 걸로 로직 수정.
    var myDiaryBoolean = writerAuthNumber == myAuthNumber
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
//                        "${day} ${diaryNumber} ${writer}",
                        "${day} ${writer}",
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                    if (myDiaryBoolean) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete),
                            contentDescription = "",
                            tint = Color(0xFF7BF579),
                            modifier = Modifier.clickable {
                                deletePublicDiary()

                            }
                        )
                    }

                }

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
                    Text(joayoNumber.toString())
                    Spacer(Modifier.size(8.dp))

                    Row (
                        modifier = Modifier.clickable {
                            savePublicDiaryBoolean = !savePublicDiaryBoolean
                            savePublicDiary()
                            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("저장하기")
                        Spacer(Modifier.size(4.dp))
                        Icon(
                            painter = if (savePublicDiaryBoolean) painterResource(id = R.drawable.savediary) else painterResource(id = R.drawable.savediary),
                            contentDescription = "Favorite",
                            tint = if (savePublicDiaryBoolean) Color.Red else Color.Gray,

                        )
                    }
                }
            }
        }
    }
}
