package com.company.dolshop.screens.screentype.bottomnavscreen


import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.screens.screentype.announcementscreen.AnnouncementScreen
import com.company.dolshop.screens.screentype.communityscreen.CommunityScreen
import com.company.dolshop.screens.screentype.mypagescreen.AddressScreen
import com.company.dolshop.screens.screentype.mypagescreen.AuthInfoScreen
import com.company.dolshop.screens.screentype.mypagescreen.GuMaeNaeYeokScreen
import com.company.dolshop.screens.screentype.mypagescreen.InputAddressInfoScreen
import com.company.dolshop.screens.screentype.mypagescreen.LogoutScreen
import com.company.dolshop.screens.screentype.mypagescreen.MyCouponScreen
import com.company.dolshop.screens.screentype.mypagescreen.MyPageScreen
import com.company.dolshop.screens.screentype.mypagescreen.SavePublicDiaryScreen
import com.company.dolshop.screens.screentype.productscreen.DetailProductScreen
import com.company.dolshop.screens.screentype.productscreen.GuMaeScreen
import com.company.dolshop.screens.screentype.productscreen.ProductScreen
import com.company.dolshop.screens.screentype.rockscreen.AddRockScreen
import com.company.dolshop.screens.screentype.rockscreen.RocksScreen
import com.company.dolshop.screens.screentype.subscreen.LoginScreen
import com.company.dolshop.screens.screentype.subscreen.PersonInfoWebView
import com.company.dolshop.screens.screentype.subscreen.SignUpScreen1
import com.company.dolshop.screens.screentype.subscreen.SingUpScreen2
import com.company.dolshop.screens.screentype.subscreen.SingUpScreen3
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.dolshop.viewmodel.UpdateBaseProductViewModel
import com.company.domain.model.DomainProductModel
import com.company.presentation.R
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.isLoggedInFlow
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavHostController) {
//    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    // navController(실제 화면 이동 담당)의 현재 상태를 구독해서 최상위의 경로가 변경될 때마다 경로를 얻어낸다.
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val context = LocalContext.current

    val dataStoreUtility = DataStoreUtility.getInstance()

    // 로그인 정보 저장된거
    val isLoggedIn by dataStoreUtility.run {
        context.isLoggedInFlow.collectAsStateWithLifecycle(
            initialValue = false
        )
    }


    // BottomBar에서 이동 할 수 있는 화면의 경로들을 정의해 놓은 리스트
    val bottomNavVisibleRoutes =
        listOf(
            ScreenList.CommunityScreen.route,
            ScreenList.ProductScreen.route,
            ScreenList.RocksScreen.route,
            ScreenList.AnnouncementScreen.route,
            ScreenList.MyPageScreen.route
        )

    val items = listOf(
        BottomNavItem(
            title = "커뮤",
            selectedIcon = R.drawable.community,
            unselectedIcon = R.drawable.community,
        ),
        BottomNavItem(
            title = "상품",
            selectedIcon = R.drawable.product,
            unselectedIcon = R.drawable.product,
        ),
        BottomNavItem(
            title = "돌's",
            selectedIcon = R.drawable.dols,
            unselectedIcon = R.drawable.dols,
        ),
        BottomNavItem(
            title = "공지",
            selectedIcon = R.drawable.announcement,
            unselectedIcon = R.drawable.announcement,
        ),
        BottomNavItem(
            title = "마이",
            selectedIcon = R.drawable.mypagi,
            unselectedIcon = R.drawable.mypagi,
        ),

        )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(2)
    }

    var route: String
    var showSplashScreen by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            // 현재 경로가 바텀네비게이션 바에서 이동 할 수 있는 경로 안에 있다면 아래 코드를 실행한다.
            if (currentRoute in bottomNavVisibleRoutes) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    items.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                // 내가 클릭한 바텀네비게이션의 아이콘의 title에 해당하는 경로로 이동한다.
                                navController.navigate(bottomNavItem.title) {
                                    // 중복 화면을 방지한다. : 내가 이동할 화면의 경로를 navigation의 스택에 쌓지 않아.
                                    launchSingleTop = true
                                }
                            },
                            label = {
                                Text(
                                    text = bottomNavItem.title,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            },
                            alwaysShowLabel = true,
                            icon = {
                                Image(
                                    painter = painterResource(id = bottomNavItem.selectedIcon),
                                    contentDescription = "test",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(MaterialTheme.colorScheme.background)
                        )
                    }
                }
            }

        }
    ) { innerPadding ->
        route = if (isLoggedIn) {
            ScreenList.RocksScreen.route
        } else {
            ScreenList.LoginScreen.route
        }


//        val dynamicLinkUri = Uri.parse("https://dolshop.page.link")
//
//        Firebase.dynamicLinks
//            .getDynamicLink(dynamicLinkUri)
//            .addOnSuccessListener() { pendingDynamicLinkData ->
//                var deepLink: Uri? = null
//                if (pendingDynamicLinkData != null) {
//                    deepLink = pendingDynamicLinkData.link
//                    Toast.makeText(context, deepLink.toString(), Toast.LENGTH_SHORT).show()
//                    Log.d("deeplinkdatat" , deepLink.toString())
//                    if(deepLink != null) {
//                        navController.navigate(ScreenList.SignUpScreen2.route)
//
//                    }
//
//                }
//            }
//            .addOnFailureListener() { e -> Log.d("deeplink", "getDynamicLink:onFailure", e) }


        NavHost(navController = navController, startDestination = route) {
            // 커뮤니티 스크린
            composable(route = ScreenList.CommunityScreen.route) {
                CommunityScreen(innerPadding)
            }
            // 커뮤니티 스크린

            // 파이어베이스 회원가입 및 로그인
            composable(route = ScreenList.SignUpScreen1.route) {
                SignUpScreen1(navController)
            }
            composable(route = ScreenList.SignUpScreen2.route) {
                SingUpScreen2(navController)
            }
            composable(route = ScreenList.SignUpScreen3.route) {
                SingUpScreen3(navController)
            }
            // 파이어베이스 회원가입 및 로그인

            // 개인 정보 처리방침
            composable(
                route = "${ScreenList.PersonInfoWebView.route}/{url}",
                arguments = listOf(navArgument("url") { type = NavType.StringType })

            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url") ?: ""
                PersonInfoWebView(url)
            }
            // 개인 정보 처리방침

            // 상품 스크린
            composable(route = ScreenList.ProductScreen.route) {
                val viewmodel = hiltViewModel<UpdateBaseProductViewModel>()
                val count by viewmodel.page.collectAsStateWithLifecycle()
                ProductScreen(innerPadding, count, navController)
            }

            composable(
                route = "${ScreenList.DetailProductScreen.route}/{dolURL}",
                arguments = listOf(navArgument("dolURL") { type = NavType.StringType })
            ) { backStackEntry ->

                val dolURL = backStackEntry.arguments?.getString("dolURL") ?: ""

                val dolJson = Gson().fromJson(dolURL, DomainProductModel::class.java)

                DetailProductScreen(dolJson, navController)
//
            }

            composable(
                route = "${ScreenList.GuMaeScreen.route}/{gumaeDolInfo}",
                arguments = listOf(navArgument("gumaeDolInfo") { type = NavType.StringType })
            ) { backStackEntry ->
                val gumaeDolInfo = backStackEntry.arguments?.getString("gumaeDolInfo") ?: ""
                val gumaeDolInfoName = gumaeDolInfo
                val real = Gson().fromJson(gumaeDolInfoName, DomainProductModel::class.java)
                GuMaeScreen(real, navController)
            }
            // 상품 스크린


            composable(route = ScreenList.RocksScreen.route) {
                val viewmodel = hiltViewModel<KakaoAuthiViewModel>()
                RocksScreen(innerPadding, viewmodel, navController)
            }

            composable(route = ScreenList.AddRockScreen.route) {
                AddRockScreen(navController)
            }

            composable(route = ScreenList.AnnouncementScreen.route) {
                AnnouncementScreen()
            }

            // 마이페이지 nested
            composable(route = ScreenList.MyPageScreen.route) {
                val viewmodel: KakaoAuthiViewModel = hiltViewModel()
                MyPageScreen(navController)
            }

            composable(route = ScreenList.AuthInfoScreen.route) {
                AuthInfoScreen(navController)
            }

            composable(route = ScreenList.LoginScreen.route) {
                val viewmodel: KakaoAuthiViewModel = hiltViewModel()
                LoginScreen(navController, viewmodel)
            }

            composable(route = ScreenList.LogoutScreen.route) {
                LogoutScreen()
            }

            composable(route = ScreenList.AddressScreen.route) {
                AddressScreen(navController, coroutineScope)
            }

            composable(
                route = "${ScreenList.InputAddressInfoScreen.route}/{gumaeDolInfo}",
                arguments = listOf(navArgument("gumaeDolInfo") { type = NavType.StringType })
            ) { backStackEntry ->

                val gumaeDolInfo = backStackEntry.arguments?.getString("gumaeDolInfo")

                //
                val gumaeDolInfoName = gumaeDolInfo
                val real = Gson().fromJson(gumaeDolInfoName, DomainProductModel::class.java)
                //

                InputAddressInfoScreen(navController , real)
            }

            composable(route = ScreenList.SavePublicDiaryScreen.route) {
                SavePublicDiaryScreen()
            }

            composable(route = ScreenList.MyCouponScreen.route) {
                MyCouponScreen()
            }

            composable(route = ScreenList.GuMaeNaeYeokScreen.route) {
                GuMaeNaeYeokScreen()
            }
        }
    }
}

