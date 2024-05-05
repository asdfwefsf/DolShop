package com.company.dolshop.screens.screentype.rockscreen

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bumptech.glide.Glide
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.ui.theme.DolShopTheme
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

@Composable
fun AddRockScreen(navController: NavController) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    Column {
        Text("AddRockScreen")
        selectImageAndDiary(navController)
    }

}


@Composable
fun selectImageAndDiary(navController : NavController) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    var diaryText by remember { mutableStateOf("") }  // 텍스트 입력을 위한 상태 변수
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImage = uri
        // uri?.let { uploadImageToFirebaseStorage(it, context, authNumber, scope) }
    }

    ImageAndDiaryScreen(selectedImage, diaryText, navController , onImageClick = {
        launcher.launch("image/*")
    }, onTextChange = { text ->
        diaryText = text  // 사용자 입력을 상태 변수에 저장
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageAndDiaryScreen(
    selectedImage: Uri? = null,
    diaryText: String,
    navController: NavController,

    onImageClick: () -> Unit,
    onTextChange: (String) -> Unit,
) {
    val kakaoAuthiViewModel : KakaoAuthiViewModel = hiltViewModel()
    val context = LocalContext.current
    val authNumber = kakaoAuthiViewModel.userInfoList.value.authNumber
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedImage != null) {
                AsyncImage(
                    model = selectedImage,
                    contentDescription = "이미지 선택",
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth()

                )
            }
            OutlinedButton(onClick = onImageClick) {
                Text(text = "이미지 선택")
            }
            // 큰 규모의 텍스트 입력창 추가
            OutlinedTextField(
                value = diaryText,
                onValueChange = onTextChange,
                label = { Text("나의 일기장") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .fillMaxHeight(0.9f),
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
                maxLines = 10,  // 스크롤 가능한 텍스트 필드로 만들기
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            Text(
                "저장하기",
                modifier = Modifier.clickable {
                    uploadImageToFirebaseStorage(
                        imageUri =  selectedImage!!,
                        context = context,
                        authNumber =  authNumber,
                        scope =  scope
                    )
                    navController.navigate(ScreenList.RocksScreen.route) {
                        navController.popBackStack()
                    }
                }
            )
        }
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


// TODO 이미지 최적화해서 Firestore Database에 이미지 저장하기
// Firestore Database에 이미지 저장
fun uploadImageToFirebaseStorage(
    imageUri: Uri,
    context: Context,
    authNumber: String,
    scope: CoroutineScope
) {
    scope.launch {
        val imageData = withContext(Dispatchers.IO) {
            // Glide로 이미지 불러와서 비트맵으로 변환 후 저장
            val bitmapData = Glide.with(context)
                .asBitmap()
                .load(imageUri)
                // TODO 이미지 사이즈 정리 : 왜 안 되는거지
                .submit(400, 400)
                .get()

            // 바이트 배열 저장소
            val baos = ByteArrayOutputStream()
            // 비트맵 압축 -> 바이트 변환 : 바이트 배열 저장소에 데이터 저장
            bitmapData.compress(Bitmap.CompressFormat.JPEG, 20, baos)
            // 바이트 배열 복사본 반환 ㅈ같네
            baos.toByteArray()
        }


        val storageRef = FirebaseStorage.getInstance().getReference("images/${imageUri.lastPathSegment}")
        val uploadTask = storageRef.putBytes(imageData)

        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                val imageUrl = uri.toString()
                saveImageUrlToRealtimeDatabase(imageUrl, authNumber)
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(context, "업로드에 실패하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

// RealTime DataBase에 이미지 URL 저장
fun saveImageUrlToRealtimeDatabase(imageUrl: String, authNumber: String) {
    val databaseRef = Firebase.database.reference
    databaseRef.child("users/$authNumber/images").push().setValue(imageUrl).addOnSuccessListener {
    }.addOnFailureListener {
    }
    val tagImageRef = databaseRef.child("images/tagNumber").push()
    tagImageRef.setValue(imageUrl)
}

//@Preview
//@Composable
//fun selectImageAndDiaryPreview() {
//    DolShopTheme {
//        selectImageAndDiary()
//    }
//}

