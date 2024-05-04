package com.company.dolshop.screens.screentype.rockscreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.company.dolshop.viewmodel.DolsViewModel
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.presentation.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import androidx.compose.foundation.lazy.items
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.ui.theme.DolShopTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

@Composable
fun RocksScreen(
    innerPadding: PaddingValues,
    viewmodel: KakaoAuthiViewModel,
    navController: NavController
) {
    val userInfolist = viewmodel.userInfoList
//    Text("RockScreen")
    Column {
        firstUI(userInfolist.value.authNicName , navController)
//        selectImage(userInfolist.value.authNumber)
        ImageTest(innerPadding)
    }

}

//Preview용 firstUI Composable Name
//@Composable
//fun firstUI(myName : String) {
@Composable
fun firstUI(myName : String , navController : NavController) {
    Row {
        Text("${myName}님의 하루 일기 ")
        Spacer(modifier = Modifier.fillMaxWidth(0.9f))
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "일기장 추가",
            modifier = Modifier.clickable {
                navController.navigate(ScreenList.AddRockScreen.route)
            }
        )
        
    }
}


//@Composable
//fun selectImage(authNumber: String) {
//    val scope = rememberCoroutineScope()
//    var selectedImage by remember { mutableStateOf<Uri?>(null) }
//    val context = LocalContext.current
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        selectedImage = uri
//        uri?.let { uploadImageToFirebaseStorage(it, context, authNumber, scope) }
//    }
//    ImageSee(selectedImage) {
//        launcher.launch("image/*")
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun ImageSee(
//    selectedImage: Uri? = null,
//    onImageClick: () -> Unit
//) {
//    Scaffold() {
//        Column(
//            Modifier
//                .fillMaxSize()
//                .padding(it),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            if (selectedImage != null) {
//                AsyncImage(model = selectedImage, contentDescription = "")
//            } else {
//
//            }
//            OutlinedButton(onClick = onImageClick) {
//                Text(text = "Choose Image")
//            }
//        }
//    }
//}

//// TODO 이미지 최적화해서 Firestore Database에 이미지 저장하기
//// Firestore Database에 이미지 저장
//fun uploadImageToFirebaseStorage(
//    imageUri: Uri,
//    context: Context,
//    authNumber: String,
//    scope: CoroutineScope
//) {
//    scope.launch {
//        val imageData = withContext(Dispatchers.IO) {
//            // Glide로 이미지 불러와서 비트맵으로 변환 후 저장
//            val bitmapData = Glide.with(context)
//                .asBitmap()
//                .load(imageUri)
//                // TODO 이미지 사이즈 정리 : 왜 안 되는거지
//                .submit(400, 400)
//                .get()
//
//            // 바이트 배열 저장소
//            val baos = ByteArrayOutputStream()
//            // 비트맵 압축 -> 바이트 변환 : 바이트 배열 저장소에 데이터 저장
//            bitmapData.compress(Bitmap.CompressFormat.JPEG, 20, baos)
//            // 바이트 배열 복사본 반환 ㅈ같네
//            baos.toByteArray()
//        }
//
//
//        val storageRef = FirebaseStorage.getInstance().getReference("images/${imageUri.lastPathSegment}")
//        val uploadTask = storageRef.putBytes(imageData)
//
//        uploadTask.addOnSuccessListener {
//            storageRef.downloadUrl.addOnSuccessListener { uri ->
//                val imageUrl = uri.toString()
//                saveImageUrlToRealtimeDatabase(imageUrl, authNumber)
//            }
//        }.addOnFailureListener { exception ->
//            Toast.makeText(context, "업로드에 실패하였습니다.", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
//
//// RealTime DataBase에 이미지 URL 저장
//fun saveImageUrlToRealtimeDatabase(imageUrl: String, authNumber: String) {
//    val databaseRef = Firebase.database.reference
//    databaseRef.child("users/$authNumber/images").push().setValue(imageUrl).addOnSuccessListener {
//    }.addOnFailureListener {
//    }
//    val tagImageRef = databaseRef.child("images/tagNumber").push()
//    tagImageRef.setValue(imageUrl)
//}

@Composable
fun ImageTest(innerPadding: PaddingValues) {
    val viewModel: DolsViewModel = hiltViewModel()
    val images: LazyPagingItems<String> = viewModel.images.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(images) { imageUrl ->
            imageUrl?.let {
                GlideImage(
                    imageUrl = it,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp), // 이미지 높이 고정
                    // TODO 로딩 할 때 이미지 -> 빈 값 이미지로 수정 예정
                    placeholder = R.drawable.ic_launcher_background,
                    // TODO 네트워크 오류 이미지 -> 빈 값 이미지로 수정 예정
                    error = R.drawable.ic_launcher_foreground
                )
            }
        }
    }
}


@Composable
fun GlideImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    placeholder: Int,
    error: Int
) {
    val startTime = remember { System.currentTimeMillis() }

    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        modifier = modifier,
        update = { imageView ->
            Glide.with(imageView.context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholder)
                .error(error)
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        val endTime = System.currentTimeMillis()
                        // 이미지 로딩 시간 체크
                        Log.d("GlideTiming", "이미지 다운로드: $imageUrl")
                        Log.d("GlideTiming", "이미지 다운로드 시간: ${endTime - startTime} ms")
                        // 이미지 뷰에 Glide에서 받아온거 넣어주기
                        imageView.setImageDrawable(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // TODO 로드 완료 되었을 때 동작
                    }
                })
        }
    )
}

//@Preview
//@Composable
//fun firstUIPreview() {
//    DolShopTheme {
//        firstUI(myName = "황건희")
//    }
//}
