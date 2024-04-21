package com.company.dolshop.ui.component.dialog

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Card
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import com.company.dolshop.designsystem.DolShopTheme
//import com.company.dolshop.designsystem.Paddings
//
//@Composable
//fun TestBaseDialogPopup(
//    dialogTitle: DialogTitle? = null,
//    dialogContent: DialogContent? = null,
//    buttons: List<DialogButton>? = null
//) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//    ) {
//        Column(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            dialogTitle?.let {
//                DialogTitleWrapper(it)
//            }
//            Column(
//                modifier = Modifier
//                    .background(Color.Transparent)
//                    .fillMaxWidth()
//                    .padding(
//                        start = Paddings.xlarge,
//                        end = Paddings.xlarge,
//                        bottom = Paddings.xlarge
//                    )
//            ) {
//                dialogContent?.let { DialogContentWrapper(it) }
//                buttons?.let { DialogButtonsColumn(it) }
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun TestBaseDialogPopup() {
//    DolShopTheme {
//        BaseDialogPopup(
//            dialogTitle = DialogTitle.Header("TITLE"),
//            dialogContent = DialogContent.Large(
//                DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde")
//            ),
//            buttons = listOf(
//                DialogButton.Primary("Okay") {}
//            )
//        )
//    }
//}
//
//@Preview
//@Composable
//fun TestBaseDialogPopup2() {
//    DolShopTheme {
//        BaseDialogPopup(
//            dialogTitle = DialogTitle.Large("TITLE"),
//            dialogContent = DialogContent.Default(
//                DialogText.Default("abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde abcde")
//            ),
//            buttons = listOf(
//                DialogButton.Secondary("Okay") {},
//                DialogButton.UnderlinedText("Cancel") {}
//            )
//        )
//    }
//}
//
//@Preview
//@Composable
//fun TestBaseDialogPopup3() {
//    DolShopTheme {
//        BaseDialogPopup(
//            dialogTitle = DialogTitle.Large("TITLE"),
//            dialogContent = DialogContent.Rating(
//                DialogText.Rating(
//                    text = "Jurassic Park",
//                    rating = 8.2f
//                )
//            ),
//            buttons = listOf(
//                DialogButton.Primary("Okay") {},
//                DialogButton.Secondary("Cancel") {}
//            )
//        )
//    }
//}