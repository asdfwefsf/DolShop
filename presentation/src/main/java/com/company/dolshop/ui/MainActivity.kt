package com.company.dolshop.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.company.dolshop.designsystem.DolShopTheme
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.dolshop.screens.screentype.bottomnavscreen.BottomNav
import com.company.dolshop.viewmodel.CoroutineWorkerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class splashScreenViewModel : ViewModel() {
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
    private val CoroutineWorkerViewModel  by viewModels<CoroutineWorkerViewModel>()
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

            DolShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNav()


                }
            }
        }

    }
}



