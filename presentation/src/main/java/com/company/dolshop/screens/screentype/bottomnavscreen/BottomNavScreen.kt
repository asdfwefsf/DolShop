package com.company.dolshop.screens.screentype.bottomnavscreen


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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.screens.screentype.announcementscreen.AnnouncementScreen
import com.company.dolshop.screens.screentype.communityscreen.CommunityScreen
import com.company.dolshop.screens.screentype.mypagescreen.AuthInfoScreen
import com.company.dolshop.screens.screentype.mypagescreen.LogoutScreen
import com.company.dolshop.screens.screentype.mypagescreen.MyPageScreen
import com.company.dolshop.screens.screentype.productscreen.ProductScreen
import com.company.dolshop.screens.screentype.rockscreen.RocksScreen
import com.company.dolshop.screens.screentype.subscreen.LoginScreen
import com.company.dolshop.viewmodel.KakaoAuthiViewModel
import com.company.dolshop.viewmodel.UpdateBaseProductViewModel
import com.company.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav() {
    val navController = rememberNavController()

    // navController(실제 화면 이동 담당)의 현재 상태를 구독해서 최상위의 경로가 변경될 때마다 경로를 얻어낸다.
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

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
            selectedIcon = R.drawable.ic_launcher_background,
            unselectedIcon = R.drawable.ic_launcher_background,
        ),
        BottomNavItem(
            title = "상품",
            selectedIcon = R.drawable.ic_launcher_background,
            unselectedIcon = R.drawable.ic_launcher_background,
        ),
        BottomNavItem(
            title = "돌's",
            selectedIcon = R.drawable.ic_launcher_background,
            unselectedIcon = R.drawable.ic_launcher_background,
        ),
        BottomNavItem(
            title = "공지",
            selectedIcon = R.drawable.ic_launcher_background,
            unselectedIcon = R.drawable.ic_launcher_background,
        ),
        BottomNavItem(
            title = "마이",
            selectedIcon = R.drawable.ic_launcher_background,
            unselectedIcon = R.drawable.ic_launcher_background,
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
//                            onClick = {
//                                // Show splash screen only when navigating to the ProductScreen
//                                selectedItemIndex = index
//                                showSplashScreen = currentRoute != ScreenList.ProductScreen.route
//                                navController.navigate(bottomNavItem.title) {
//                                    launchSingleTop = true
//                                }
//                            },
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
    ) {
        innerPadding ->
        if (false) {
            route = ScreenList.LoginScreen.route
        } else {
            route = ScreenList.ProductScreen.route
        }
        NavHost(navController = navController, startDestination = route) {
            composable(route = ScreenList.CommunityScreen.route) {
                CommunityScreen()
            }


            composable(route = ScreenList.ProductScreen.route) {
                val viewmodel = hiltViewModel<UpdateBaseProductViewModel>()
                val count by viewmodel.page.collectAsStateWithLifecycle()
                ProductScreen(innerPadding , count)
            }

            composable(route = ScreenList.RocksScreen.route) {
                RocksScreen()
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
        }
    }
}

