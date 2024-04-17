package com.company.dolshop.screens.screentype.bottomnavscreen


import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.screens.screentype.announcementScreen.AnnouncementScreen
import com.company.dolshop.screens.screentype.communityScreen.CommunityScreen
import com.company.dolshop.screens.screentype.mypageScreen.MyPageScreen
import com.company.dolshop.screens.screentype.productScreen.ProductScreen
import com.company.dolshop.screens.screentype.rockScreen.RockScreen
import com.company.presentation.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BottomNav() {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val bottomNavVisibleRoutes =
        listOf(
            ScreenList.CommunityScreen.route,
            ScreenList.ProductScreen.route,
            ScreenList.RockScreen.route,
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
        mutableStateOf(1)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (currentRoute in bottomNavVisibleRoutes) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    items.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(bottomNavItem.title) {
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
        it
        NavHost(navController = navController, startDestination = ScreenList.RockScreen.route) {
            composable(route = ScreenList.CommunityScreen.route) {
                CommunityScreen()
            }

            composable(route = ScreenList.ProductScreen.route) {
                ProductScreen()
            }

            composable(route = ScreenList.RockScreen.route) {
                RockScreen()
            }

            composable(route = ScreenList.AnnouncementScreen.route) {
                AnnouncementScreen()
            }

            composable(route = ScreenList.MyPageScreen.route) {
                MyPageScreen()
            }
        }
    }
}

