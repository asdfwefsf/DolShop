package com.company.dolshop.screens.screentype.productscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.utility.decodeUrl

@Composable
fun DetailProductScreen(dolURL : String) {
    val decodedDolUrl = decodeUrl(dolURL)

    Column{
        Text("Detail Product")
        Text("${decodedDolUrl}")
        LoadImageWithCoil(imageUrl = decodedDolUrl, context = LocalContext.current)
    }

}