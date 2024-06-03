package com.company.data.datasource.userinfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.company.data.datasource.baseproductinfo1.BaseProductInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {
    @Upsert
    suspend fun upsertUserInfo(userInfo: UserInfo)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserInfo(userInfo: UserInfo)
    @Update
    fun updateUserInfo(userInfo: UserInfo)
    @Delete
    suspend fun deleteUserInfo(userInfo: UserInfo)
    @Query("SELECT * FROM userinfo ORDER BY id DESC")
    fun getUserInfo() : Flow<UserInfo>
    @Query("SELECT EXISTS(SELECT 1 FROM userinfo WHERE id = :id)")
    fun prodideUserInfoExists(id: Int): Boolean
}















//@Composable
//fun ImageTest(innerPadding: PaddingValues) {
//    val viewModel: DolsViewModel = hiltViewModel()
//    val diaries: LazyPagingItems<Diary> = viewModel.diaryEntries.collectAsLazyPagingItems()
//
//    LazyColumn(
//        modifier = Modifier
//            .padding(innerPadding)
//            .fillMaxWidth()
//    ) {
//        items(diaries) { diaryEntry ->
//            diaryEntry?.let { diary ->
//                // 이미지 로딩
//                val imageBitmap: MutableState<Bitmap?> = remember { mutableStateOf(null) }
//
//                Glide.with(LocalContext.current)
//                    .asBitmap()
//                    .load(diary.image)
//                    .into(object : CustomTarget<Bitmap>() {
//                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                            imageBitmap.value = resource
//                        }
//
//                        override fun onLoadCleared(placeholder: Drawable?) {}
//                    })
//
//                Column(modifier = Modifier.padding(vertical = 8.dp)) {
//                    // 이미지 표시
//                    imageBitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
//                        Image(
//                            bitmap = fetchedBitmap,
//                            contentDescription = "Image for diary entry",  // Accessibility description
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(200.dp)  // 고정된 이미지 높이
//                        )
//                    } ?: Image(
//                        painter = painterResource(id = R.drawable.ic_launcher_background),
//                        contentDescription = null,
//                        contentScale = ContentScale.Fit,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    // 일기 텍스트 표시
//                    Text(
//                        text = diary.diary,
//                        style = MaterialTheme.typography.subtitle1,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}
