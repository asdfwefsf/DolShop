package com.company.dolshop.ui

import android.animation.ObjectAnimator
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.company.designsystem.designsystem.DolShopTheme
import com.company.dolshop.screens.ScreenList
import com.company.dolshop.screens.screentype.bottomnavscreen.BottomNav
import com.company.dolshop.viewmodel.CoroutineWorkerViewModel
import com.company.utility.DataStoreUtility
import com.company.utility.DataStoreUtility.Companion.isSplashFlow
import com.company.utility.DataStoreUtility.Companion.setSplashState
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class splashScreenViewModel(

) : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()
    init {
        viewModelScope.launch {
            delay(10L)
            _isReady.value = true
        }
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel by viewModels<splashScreenViewModel>()
    private val CoroutineWorkerViewModel by viewModels<CoroutineWorkerViewModel>()

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.1f,
                    2f,
                    1.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 1500L
                zoomX.doOnEnd { screen.remove() }
                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.1f,
                    2f,
                    1.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 1500L
                zoomY.doOnEnd { screen.remove() }
                zoomX.start()
                zoomY.start()
            }
        }

        setContent {


            CoroutineWorkerViewModel.test(this)
            navController = rememberNavController()
            // 딥링크 씨발
            val dynamicLinkUri = Uri.parse("https://dolshop.page.link")
            Firebase.dynamicLinks
                .getDynamicLink(dynamicLinkUri)
                .addOnSuccessListener() { pendingDynamicLinkData ->
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.link
                        Toast.makeText(this, deepLink.toString(), Toast.LENGTH_SHORT).show()
                        Log.d("deeplinkdatat", deepLink.toString())
                        if (deepLink != null) {
                            navController.navigate(ScreenList.SignUpScreen2.route)
//                            splashViewModel.setReadyState(false)
                        }

                    }
                }
                .addOnFailureListener() { e -> Log.d("deeplink", "getDynamicLink:onFailure", e) }
            // 딥링크 씨발


            DolShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNav(navController)
                }
            }
        }

    }


}