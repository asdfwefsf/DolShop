package com.company.dolshop.screens

sealed class ScreenList(val route : String) {
    object CommunityScreen : ScreenList("커뮤")
    object ProductScreen : ScreenList("상품")
    object RocksScreen : ScreenList("돌's")
    object AnnouncementScreen : ScreenList("공지")
    object MyPageScreen : ScreenList("마이")
}