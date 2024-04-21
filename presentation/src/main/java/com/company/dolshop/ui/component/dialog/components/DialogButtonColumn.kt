package com.company.dolshop.ui.component.dialog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.company.dolshop.designsystem.DolShopTheme
import com.company.dolshop.designsystem.Paddings
import com.company.dolshop.ui.component.dialog.DialogButton
import com.company.dolshop.ui.component.dialog.PrimaryButton
import com.company.dolshop.ui.component.dialog.SecondaryButton

@Composable
fun TestDialogButtonsColumn(
    buttons: List<DialogButton>?
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons?.forEachIndexed { index, dialogButton ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(Paddings.large))
            }
            when (dialogButton) {
                is DialogButton.Primary -> {
                    PrimaryButton(
                        text = dialogButton.title,
//                        leadingIconData = dialogButton.leadingIconData
                    ) { dialogButton.action?.invoke() }
                }

                is DialogButton.Secondary -> {
                    SecondaryButton(
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }

            }
        }
    }
}
