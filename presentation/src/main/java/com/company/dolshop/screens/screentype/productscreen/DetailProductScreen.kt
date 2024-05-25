package com.company.dolshop.screens.screentype.productscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.company.designsystem.designsystem.component.loadcoil.LoadDetailProductScreen
import com.company.designsystem.designsystem.component.loadcoil.LoadImageWithCoil
import com.company.domain.model.DomainProductModel
import com.company.utility.decodeUrl
import com.company.utility.encodeUrl

@Composable
fun DetailProductScreen(dolURL : DomainProductModel) {
    val decodedDolUrl = decodeUrl(dolURL.toString())

    Column{
        Text("Detail Product")
        Text(dolURL.text1)
        Text(dolURL.text2)
        Text(dolURL.text3)
        Text(dolURL.text4)
        Text(dolURL.text5)

        LoadDetailProductScreen(
            imageUrl = dolURL.image1,
            context = LocalContext.current,
            modifier = Modifier.fillMaxWidth()


        )
    }

}

//@Preview
//@Composable
//fun LoadDetailProductScreenPreview() {
//    LoadDetailProductScreen(
//        imageUrl = "https://m.segye.com/content/image/2021/11/16/20211116509557.jpg",
//        context = LocalContext.current,
//        modifier = Modifier.fillMaxSize())
//}
//
//@Preview
//@Composable
//fun DetailProductScreenPreview() {
//
//    val encodedUrl = encodeUrl("https://m.segye.com/content/image/2021/11/16/20211116509557.jpg")
//    DetailProductScreen(encodedUrl)
//    DetailProductScreen(
//        "https://m.segye.com/content/image/2021/11/16/20211116509557.jpg"
//    )
//}
//

