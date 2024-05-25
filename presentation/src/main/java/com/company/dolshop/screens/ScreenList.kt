package com.company.dolshop.screens

sealed class ScreenList(val route : String) {
    object LoginScreen : ScreenList("로그인")

    // 커뮤니티
    object CommunityScreen : ScreenList("커뮤")

    // 상품
    object ProductScreen : ScreenList("상품")
    object DetailProductScreen : ScreenList("특별한돌/{dolURL}")
    object GuMaeScreen : ScreenList("구매화면/{gumaeDolInfo}")
    // Dol's
    object RocksScreen : ScreenList("돌's")
    object AddRockScreen : ScreenList("돌 더하기")

    object AnnouncementScreen : ScreenList("공지")


    // 마이 페이지
    object MyPageScreen : ScreenList("마이")
    object AuthInfoScreen : ScreenList("개인정보")
    object LogoutScreen : ScreenList("로그아웃")
    object InputAddressInfoScreen : ScreenList("배송지 정보 입력")
    object AddressScreen : ScreenList("주소")
    object SavePublicDiaryScreen : ScreenList("저장일기")
    object MyCouponScreen : ScreenList("마이쿠폰")
}