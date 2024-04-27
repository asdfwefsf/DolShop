package com.company.dolshop.screens.screentype.announcementscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.dolshop.viewmodel.AnnouncementViewModel

@Composable
fun AnnouncementScreen() {
    val announcementViewModel : AnnouncementViewModel = hiltViewModel()

    Text("AnnouncementScreen")


}