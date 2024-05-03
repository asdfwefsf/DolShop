package com.company.dolshop.screens.screentype.rockscreen

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.company.dolshop.ui.theme.DolShopTheme
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RocksScreen(
    viewmodel : KakaoAuthiViewModel
) {

    val userInfolist = viewmodel.userInfoList

    Text("RockScreen")
    MainApp(userInfolist.value.authNumber)
}

@Composable
fun MainApp(authNumber : String) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImage = uri
        uri?.let { uploadImageToFirebaseStorage(it, context , authNumber) } // 이미지 선택 후 업로드 실행
    }

    MainContent(selectedImage) {
        launcher.launch("image/*") // 모든 이미지 타입을 허용
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent(
    selectedImage: Uri? = null,
    onImageClick: () -> Unit
) {
    Scaffold() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedImage != null)
                Image(
                    painter = rememberImagePainter(selectedImage),
                    contentDescription = "Selected image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onImageClick()
                        })
            else
                OutlinedButton(onClick = onImageClick) {
                    Text(text = "Choose Image")
                }
        }
    }
}


fun uploadImageToFirebaseStorage(imageUri: Uri, context: Context , authNumber : String) {
    val storageRef = FirebaseStorage.getInstance().getReference("images/${imageUri.lastPathSegment}")
    val uploadTask = storageRef.putFile(imageUri)

    uploadTask.addOnSuccessListener {
        // 업로드 성공 후 URL을 가져옵니다.
        storageRef.downloadUrl.addOnSuccessListener { uri ->
            val imageUrl = uri.toString() // HTTPS 형식의 URL
            saveImageUrlToRealtimeDatabase(imageUrl , authNumber) // Realtime Database에 URL 저장
        }
    }.addOnFailureListener { exception ->
        // 에러 처리
        Toast.makeText(context, "Upload failed: ${exception.message}", Toast.LENGTH_SHORT).show()
    }
}

fun saveImageUrlToRealtimeDatabase(imageUrl: String , authNumber : String) {
    val databaseRef = Firebase.database.reference
    databaseRef.child("users/$authNumber/images").push().setValue(imageUrl).addOnSuccessListener {
        // 데이터베이스 업데이트 성공 시 처리
    }.addOnFailureListener {
        // 데이터베이스 업데이트 실패 시 처리
    }

    val tagImageRef = databaseRef.child("images/tagNumber").push()
    tagImageRef.setValue(imageUrl).addOnSuccessListener {
        // 태그 경로 데이터베이스 업데이트 성공 시 처리
    }.addOnFailureListener {
        // 태그 경로 데이터베이스 업데이트 실패 시 처리
    }
}
//@Preview
//@Composable
//private fun Preview() {
//    DolShopTheme() {
//        MainContent() {
//        }
//    }
//}
//
//@Preview
//@Composable
//private fun PreviewSelected() {
//    DolShopTheme() {
//        MainContent(Uri.EMPTY) {
//
//        }
//    }
//}