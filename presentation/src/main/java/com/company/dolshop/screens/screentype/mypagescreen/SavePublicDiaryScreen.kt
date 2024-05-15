package com.company.dolshop.screens.screentype.mypagescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.company.designsystem.designsystem.component.card.SomenailCard
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.dolshop.viewmodel.publicdiary.PublicDiaryViewModel
import com.company.domain.entity.PublicDiary

@Composable
fun SavePublicDiaryScreen(
    navController: NavController,
) {

    val publicDiaryviewModel: PublicDiaryViewModel = hiltViewModel()

    val publicDiaryList by publicDiaryviewModel.publicDiary.collectAsState()




    LazyColumn {
        items(publicDiaryList.size) { index ->
            publicDiaryList[index]?.let {
                PublicDiaryItem(publicDiary = it)
            }

        }
    }
}

@Composable
fun PublicDiaryItem(publicDiary: PublicDiary) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = publicDiary.writer)
        Text(text = publicDiary.day)
        Image(
            painter = rememberImagePainter(publicDiary.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}