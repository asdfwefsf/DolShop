package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AddressScreen(navController: NavController , coroutineScope: CoroutineScope) {

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    // Android -> JavaScript
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        view?.loadUrl("javascript:sample2_execDaumPostcode();")
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl("https://dolshop-aa5e8.web.app")
                addJavascriptInterface(BridgeInterface(navController , coroutineScope), "Android")
            }
        },
    )
}

class BridgeInterface(
    val navController: NavController,
    val coroutineScope: CoroutineScope
) {
    // JavaScript -> Android
    @JavascriptInterface
    fun processDATA(data: String) {
        Log.d("WebView", "Received data: $data")
        coroutineScope.launch {
            navController.navigate(ScreenList.AuthInfoScreen.route)

        }
    }

}