package com.company.dolshop.screens.screentype.announcementscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.viewmodel.AnnouncementViewModel

@Composable
fun AnnouncementScreen() {
    val announcementViewModel : AnnouncementViewModel = hiltViewModel()
    val announcementList = announcementViewModel.announcement.collectAsState()

    Text("AnnouncementScreen")
    

}