package com.company.dolshop.screens

sealed class ScreenList(val route : String) {
    object LoginScreen : ScreenList("로그인")

    object CommunityScreen : ScreenList("커뮤")
    object ProductScreen : ScreenList("상품")
    object RocksScreen : ScreenList("돌's")
    object AnnouncementScreen : ScreenList("공지")


    // 마이 페이지
    object MyPageScreen : ScreenList("마이")
    object AuthInfoScreen : ScreenList("개인정보")
    object LogoutScreen : ScreenList("로그아웃")
}