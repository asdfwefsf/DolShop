package com.company.dolshop.screens.screentype.mypagescreen

import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.company.dolshop.screens.ScreenList
import com.company.domain.model.DomainProductModel
import com.company.utility.encodeUrl
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

@Composable
fun AddressScreen(navController: NavController, coroutineScope: CoroutineScope , gumaeDolInfo : DomainProductModel) {

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
//                addJavascriptInterface(WebAppInterface(this), "AndroidBridge")

                loadUrl("https://dolshop-aa5e8.web.app")
                addJavascriptInterface(
                    BridgeInterface(navController, coroutineScope, this , gumaeDolInfo),
                    "Android"
                )

            }
        },


        )

}


class BridgeInterface(
    val navController: NavController,
    val coroutineScope: CoroutineScope,
    val webView: WebView,
    val gumaeDolInfo : DomainProductModel
) {
    // JavaScript -> Android
    @JavascriptInterface
    fun processDATA(jsonData: String) {
        Log.d("WebView", "Received JSON: $jsonData")
        val jsonString = jsonData
        val parts = jsonString.split(",")
        val addressNumber = parts[0].trim()
        val address = parts[1].trim()
        val additionalInfo = if (parts.size > 2) parts[2].trim() else ""
        coroutineScope.launch {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                set("addressNumber", addressNumber)
                set("address", address + additionalInfo)
                set("change", true)

//                if (navController.previousBackStackEntry?.destination?.route != "개인정보") {
//                    set("HARD", gumaeDolInfo)
//
//                }
                Log.d(
                    "NavigationData",
                    "Data set - AddressNumber: $addressNumber, Address: $address $additionalInfo"
                )
            }
            withContext(Dispatchers.Main) {
//                var gumaeDolInfo = DomainProductModel("","","","","","","","","","")

//                val gumaeDolInfo =
//                    navController.previousBackStackEntry?.savedStateHandle?.get<DomainProductModel>("HARD") ?: DomainProductModel("", "", "", "", "", "", "", "", "", "")

                val encodedProductInfo =
                    encodeUrl(Gson().toJson(gumaeDolInfo, DomainProductModel::class.java))

                navController.navigate("${ScreenList.InputAddressInfoScreen.route}/${encodedProductInfo}") {
                    navController.popBackStack()

                    launchSingleTop = true
                }

                webView.apply {
                    clearHistory()
                    clearCache(true)
                    loadUrl("about:blank")
                    destroy()
                }
//                navController.previousBackStackEntry?.savedStateHandle?.apply {
//                    set("addressNumber", addressNumber)
//                    set("address", address + additionalInfo)
//                    set("change", true)
//                    if(navController.previousBackStackEntry?.destination?.route != "개인정보") {
//                        set("HARD" , gumaeDolInfo)
//
//                    }
//                    Log.d(
//                        "NavigationData",
//                        "Data set - AddressNumber: $addressNumber, Address: $address $additionalInfo"
//                    )
//                }
            }


        }

    }

//    function deleteWebView() {
//        // 수행할 작업
//        // 작업 완료 후 안드로이드 메소드 호출
//        console.log("Calling Android interface");
//
//        window.AndroidBridge.destroyWebView();
//    }
}

//class WebAppInterface(val webView: WebView , val coroutineScope: CoroutineScope) {
//    @JavascriptInterface
//    fun destroyWebView() {
//        coroutineScope.launch {
//            Log.d("WebAppInterface", "Attempting to destroy WebView")
//            webView.post {
//                if (webView != null) {
//                    Log.d("WebAppInterface", "WebView is being destroyed")
//                    webView.destroy()
//                } else {
//                    Log.d("WebAppInterface", "WebView is null or already destroyed")
//                }
//            }
//        }
//
//    }
//}
//addJavascriptInterface(WebAppInterface(this , coroutineScope), "AndroidBridge")
